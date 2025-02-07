package org.eduorg.msauth.common.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.eduorg.msauth.auth.infraestructure.repository.TokenRepository;
import org.eduorg.msauth.auth.infraestructure.services.JwtService;
import org.eduorg.msauth.user.infraestructure.model.OdmUserEntity;
import org.eduorg.msauth.user.infraestructure.repository.MongoUserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final MongoUserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

//        logger.debug("Request path: " + request.getServletPath());
//        logger.debug("Authorization header: " + request.getHeader(HttpHeaders.AUTHORIZATION));

        if (request.getServletPath().contains("/api/v1/auth")) {
//            logger.debug("Bypassing authentication for auth endpoint");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            logger.debug("No or invalid Authorization header");
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(jwt);
//        logger.debug("Extracted user email from JWT: " + userEmail);

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userEmail == null || authentication != null) {
//            logger.debug("User email is null or user is already authenticated");
            filterChain.doFilter(request, response);
            return;
        }

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        final boolean isTokenExpiredRevoked = tokenRepository.findByToken(jwt)
                .map(token -> !token.isExpired() && !token.isRevoked())
                .orElse(false);

        if (isTokenExpiredRevoked) {
            final Optional<OdmUserEntity> user = userRepository.findByEmail(userEmail);
            if (user.isPresent()) {
                final boolean isTokenValid = jwtService.isTokenValid(jwt, user.get());
                //            logger.debug("Is token valid? " + isTokenValid);

                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                    logger.debug("User authenticated: " + userEmail);
                } else {
                    tokenRepository.save(Objects.requireNonNull(tokenRepository.findByToken(jwt)
                            .map(token -> {
                                token.setExpired(true);
                                return token;
                            }).orElse(null)));
                }

            }
        }

        filterChain.doFilter(request, response);
    }

}
