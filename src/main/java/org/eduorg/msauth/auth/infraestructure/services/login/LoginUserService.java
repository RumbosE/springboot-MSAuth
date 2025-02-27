package org.eduorg.msauth.auth.infraestructure.services.login;

import org.eduorg.msauth.auth.infraestructure.jwt_generator.JwtGenerator;
import org.eduorg.msauth.auth.infraestructure.repository.Token;
import org.eduorg.msauth.auth.infraestructure.repository.TokenRepository;
import org.eduorg.msauth.auth.infraestructure.services.login.dtos.LoginUserEntryDto;
import org.eduorg.msauth.auth.infraestructure.services.login.dtos.LoginUserResponseDto;
import org.eduorg.msauth.common.application.jwt_generator.dto.DataJwt;
import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.infraestructure.model.OdmUserEntity;
import org.eduorg.msauth.user.infraestructure.repository.MongoUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;


@Service
public class LoginUserService implements IService<LoginUserEntryDto, LoginUserResponseDto> {

    private final MongoUserRepository userRepo;
    private final TokenRepository tokenRepo;

    private final JwtGenerator jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginUserService(
            MongoUserRepository userRepo, TokenRepository tokenRepo, JwtGenerator jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Result<LoginUserResponseDto> execute(LoginUserEntryDto data) {
        try{

            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword())
            );

            if ( !authentication.isAuthenticated()) {
                throw new BadCredentialsException("Bad credentials");
            }

            final OdmUserEntity user = userRepo.findByEmail( data.getEmail() )
                    .orElseThrow(LoginException::new );

            var token = jwtService.generate( new DataJwt(
                    user.getId(),
                    user.getEmail()
            ));

            saveUserToken(user.getId().toString(), token);

            return Result.makeResult( new LoginUserResponseDto(
                    user.getId().toString(),
                    user.getEmail(),
                    token
            ));

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return Result.makeFailure( new LoginException() );
        }

    }

    private void saveUserToken(String userId, String jwtToken) {
        final Token token = Token.builder()
                .userId(userId)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepo.save(token);
    }
}
