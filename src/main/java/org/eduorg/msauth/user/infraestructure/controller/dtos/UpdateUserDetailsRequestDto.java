package org.eduorg.msauth.user.infraestructure.controller.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
public class UpdateUserDetailsRequestDto {

    @Length(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @Length(min = 3, message = "Name should be at least 3 characters")
    private String lastname;

    @Length(min = 3, message = "Phone code should be at least 3 characters")
    private String phoneCode;

    @Length(min = 3, message = "Phone number should be at least 3 characters")
    @Length(max = 12, message = "Phone number should be at most 12 characters")
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthdate;

    @Pattern(regexp = "[MFO]", message="Gender must be F, M, or O")
    private String gender;
}
