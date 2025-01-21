package org.eduorg.msauth.user.infraestructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.eduorg.msauth.common.application.aspects.LoggerAspect;
import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.application.SingUpApplicationService;
import org.eduorg.msauth.user.application.dto.SignUpApplicationEntryDto;
import org.eduorg.msauth.user.domain.repository.IUserRepository;
import org.eduorg.msauth.user.infraestructure.dto.RegisterUserEntryDto;
import org.eduorg.msauth.user.application.dto.SignUpApplicationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/signup")
@RequiredArgsConstructor
public class RegisterUserController {

    final IUserRepository OdmUserRepositoryImpl;


    @PostMapping
    public ResponseEntity<SignUpApplicationResponseDto> execute( @Valid @RequestBody RegisterUserEntryDto user )  throws Exception {
        System.out.println("RegisterUserController.execute");



        IService<SignUpApplicationEntryDto, SignUpApplicationResponseDto> service =
                new LoggerAspect<>(
                        new SingUpApplicationService(OdmUserRepositoryImpl)
        );

        SignUpApplicationEntryDto dto = new SignUpApplicationEntryDto(
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getLastname(),
                user.getPhoneCode(),
                user.getPhoneNumber(),
                user.getBirthdate());

        Result<SignUpApplicationResponseDto> res = service.execute(dto);

        if( res.isSuccess() ) {
            return new ResponseEntity<>(res.getResult(),HttpStatus.CREATED);
        } else {
            throw res.getFailure();
        }
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("RegisterUserController.test");
        return "Test";
    }
}
