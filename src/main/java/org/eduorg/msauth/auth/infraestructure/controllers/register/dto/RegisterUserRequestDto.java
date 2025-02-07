package org.eduorg.msauth.auth.infraestructure.controllers.register.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class RegisterUserRequestDto {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 6, message = "Password should be at least 6 characters")
    private String password;

    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, message = "Name should be at least 3 characters")
    private String lastname;

    @NotBlank(message = "Phone code is mandatory")
    @Length(min = 3, message = "Phone code should be at least 3 characters")
    private String phoneCode;

    @NotBlank(message = "Phone number is mandatory")
    @Length(min = 3, message = "Phone number should be at least 3 characters")
    @Length(max = 12, message = "Phone number should be at most 12 characters")
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotNull(message = "Birthdate is mandatory")
    private LocalDate birthdate;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "[MFO]", message="Gender must be F, M, or O")
    private String gender;
}
