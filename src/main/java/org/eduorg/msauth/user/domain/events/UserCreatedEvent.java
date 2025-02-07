package org.eduorg.msauth.user.domain.events;

import org.eduorg.msauth.common.domain.domain_events.DomainEvents;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class UserCreatedEvent extends DomainEvents {
    public final UUID id;
    public final String name;
    public final String email;
    public final String phone;
    public final LocalDate birthdate;
    public final String gender;

    private UserCreatedEvent (
            UUID id, String name, String email, String phone, LocalDate date, String gender
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthdate = date;
        this.gender = gender;
    }

    public static UserCreatedEvent create(
            UUID id, String name, String email, String phone, LocalDate date, String gender
    ) {
        return new UserCreatedEvent(id, name, email, phone, date, gender);
    }

}
