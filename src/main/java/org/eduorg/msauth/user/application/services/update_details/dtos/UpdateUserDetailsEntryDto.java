package org.eduorg.msauth.user.application.services.update_details.dtos;

import java.time.LocalDate;
import java.util.Date;

public record UpdateUserDetailsEntryDto(
        String email,
        String name,
        String lastname,
        String phoneCode,
        String phoneNumber,
        LocalDate birthdate,
        String gender
) {
}
