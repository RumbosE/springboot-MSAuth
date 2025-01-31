package org.eduorg.msauth.user.infraestructure.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.eduorg.msauth.auth.infraestructure.services.JwtService;
import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.application.services.user_details.GetUserDetailsService;
import org.eduorg.msauth.user.application.services.user_details.dtos.UserDetailsEntryDto;
import org.eduorg.msauth.user.application.services.user_details.dtos.UserDetailsResponseDto;
import org.eduorg.msauth.user.infraestructure.repository.UserRepositoryImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class GetUserDetailsController {

    private final UserRepositoryImpl userRepository;
    private final JwtService jwtService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<UserDetailsResponseDto> execute() throws Exception {
        final String jwt = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        final String userEmail = jwtService.extractUsername(jwt);

        final IService<UserDetailsEntryDto, UserDetailsResponseDto> service = new GetUserDetailsService(userRepository);

        final Result<UserDetailsResponseDto> result = service.execute(new UserDetailsEntryDto(userEmail));

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getResult());
        } else {
            throw result.getFailure();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }

}
