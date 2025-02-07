package org.eduorg.msauth.user.application.services.user_details.dtos;


import java.time.LocalDate;
import java.util.Date;

public record UserDetailsResponseDto(
        String id,
        String name,
        String lastName,
        String email,
        String phone,
        LocalDate birthDate,
        String gender
) {
}
