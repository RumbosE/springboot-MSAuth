package org.eduorg.msauth.user.infraestructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RegisterUserEntryDto {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    String email;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 6, message = "Password should be at least 6 characters")
    String password;


    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, message = "Name should be at least 3 characters")
    String name;

    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, message = "Name should be at least 3 characters")
    String lastname;

    @NotBlank(message = "Phone code is mandatory")
    @Length(min = 3, message = "Phone code should be at least 3 characters")
    String phoneCode;

    @NotBlank(message = "Phone number is mandatory")
    @Length(min = 3, message = "Phone number should be at least 3 characters")
    @Length(max = 12, message = "Phone number should be at most 12 characters")
    String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    Date birthdate;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "[MFO]", message="Gender must be G, M, or O")
    String gender;
}
