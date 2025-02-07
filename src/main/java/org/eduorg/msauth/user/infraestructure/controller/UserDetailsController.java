package org.eduorg.msauth.user.infraestructure.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.eduorg.msauth.auth.infraestructure.services.JwtService;
import org.eduorg.msauth.common.application.aspects.logger.LoggerAspect;
import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.infraestructure.logger.LoggerAspectImpl;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.application.services.update_details.UpdateUserDetailsService;
import org.eduorg.msauth.user.application.services.update_details.dtos.UpdateUserDetailsEntryDto;
import org.eduorg.msauth.user.application.services.update_details.dtos.UpdateUserDetailsResponseDto;
import org.eduorg.msauth.user.application.services.user_details.GetUserDetailsService;
import org.eduorg.msauth.user.application.services.user_details.dtos.UserDetailsEntryDto;
import org.eduorg.msauth.user.application.services.user_details.dtos.UserDetailsResponseDto;
import org.eduorg.msauth.user.infraestructure.controller.dtos.UpdateUserDetailsRequestDto;
import org.eduorg.msauth.user.infraestructure.repository.UserRepositoryImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserRepositoryImpl userRepository;
    private final JwtService jwtService;
    private final HttpServletRequest request;
    private final LoggerAspectImpl loggerAspectImpl;

    @GetMapping
    public ResponseEntity<UserDetailsResponseDto> getUserDetails() throws Exception {
        final String jwt = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        final String userEmail = jwtService.extractUsername(jwt);

        final IService<UserDetailsEntryDto, UserDetailsResponseDto> service = new LoggerAspect<>(
                new GetUserDetailsService(userRepository), loggerAspectImpl);

        final Result<UserDetailsResponseDto> result = service.execute(new UserDetailsEntryDto(userEmail));

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getResult());
        } else {
            throw result.getFailure();
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<UpdateUserDetailsResponseDto> updateUserDetails(@Valid @RequestBody UpdateUserDetailsRequestDto userDetailsRequestDto)
            throws Exception {
        final String jwt = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        final String userEmail = jwtService.extractUsername(jwt);

        final IService<UpdateUserDetailsEntryDto, UpdateUserDetailsResponseDto> service = new LoggerAspect<>(
                new UpdateUserDetailsService(userRepository), loggerAspectImpl);

        final Result<UpdateUserDetailsResponseDto> result = service.execute(new UpdateUserDetailsEntryDto(
                userEmail,
                userDetailsRequestDto.getName(),
                userDetailsRequestDto.getLastname(),
                userDetailsRequestDto.getPhoneCode(),
                userDetailsRequestDto.getPhoneNumber(),
                userDetailsRequestDto.getBirthdate(),
                userDetailsRequestDto.getGender()));

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getResult());
        } else {
            throw result.getFailure();
        }
    }


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test !!!");
    }

}
