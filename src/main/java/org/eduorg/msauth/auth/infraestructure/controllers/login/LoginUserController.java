package org.eduorg.msauth.auth.infraestructure.controllers.login;

import jakarta.validation.Valid;
import org.eduorg.msauth.auth.infraestructure.controllers.login.dto.LoginRequestDto;
import org.eduorg.msauth.auth.infraestructure.services.login.LoginUserService;
import org.eduorg.msauth.auth.infraestructure.services.login.dtos.LoginUserEntryDto;
import org.eduorg.msauth.auth.infraestructure.services.login.dtos.LoginUserResponseDto;
import org.eduorg.msauth.common.utils.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/login")
public class LoginUserController {

    private final LoginUserService service;

    
    public LoginUserController(LoginUserService service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoginUserResponseDto> login( @Valid @RequestBody LoginRequestDto loginDto ) throws Exception {

        final LoginUserEntryDto dto = new LoginUserEntryDto(
                loginDto.getEmail(),
                loginDto.getPassword()
        );

        final Result<LoginUserResponseDto> res = service.execute(dto);

        if (res.isSuccess()) {
            return ResponseEntity.ok(res.getResult());
        } else {
            throw res.getFailure();
        }
    }
}
