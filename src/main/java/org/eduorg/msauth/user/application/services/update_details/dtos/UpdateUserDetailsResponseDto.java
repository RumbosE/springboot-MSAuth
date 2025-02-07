package org.eduorg.msauth.user.application.services.update_details.dtos;

public record UpdateUserDetailsResponseDto(
        String id,
        String email,
        String name,
        String lastname,
        String phoneCode,
        String phoneNumber,
        String birthdate,
        String gender
) {
}
