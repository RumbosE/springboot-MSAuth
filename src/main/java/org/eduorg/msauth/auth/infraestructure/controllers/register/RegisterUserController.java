package org.eduorg.msauth.auth.infraestructure.controllers.register;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.eduorg.msauth.common.application.aspects.logger.LoggerAspect;
import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.infraestructure.logger.LoggerAspectImpl;
import org.eduorg.msauth.common.infraestructure.password_encoder.PasswordEncoderImpl;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.application.services.sing_up.SingUpApplicationService;
import org.eduorg.msauth.user.application.services.sing_up.dto.SignUpApplicationEntryDto;
import org.eduorg.msauth.auth.infraestructure.controllers.register.dto.RegisterUserEntryDto;
import org.eduorg.msauth.user.application.services.sing_up.dto.SignUpApplicationResponseDto;
import org.eduorg.msauth.user.infraestructure.repository.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/signup")
@RequiredArgsConstructor
public class RegisterUserController {

    final UserRepositoryImpl userRepository;
    final LoggerAspectImpl loggerAspect;
    final PasswordEncoderImpl passwordEncoder;

    @PostMapping
    public ResponseEntity<SignUpApplicationResponseDto> execute( @Valid @RequestBody RegisterUserEntryDto user )  throws Exception {

        SignUpApplicationEntryDto dto = new SignUpApplicationEntryDto(
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getLastname(),
                user.getPhoneCode(),
                user.getPhoneNumber(),
                user.getBirthdate(),
                user.getGender()
        );

        IService<SignUpApplicationEntryDto, SignUpApplicationResponseDto> service = new LoggerAspect<>(
                new SingUpApplicationService( userRepository, passwordEncoder ),
                new LoggerAspectImpl()
        );

        Result<SignUpApplicationResponseDto> res = service.execute(dto);

        if( res.isSuccess() ) {
            return new ResponseEntity<>(res.getResult(),HttpStatus.CREATED);
        } else {
            throw res.getFailure();
        }
    }
}
