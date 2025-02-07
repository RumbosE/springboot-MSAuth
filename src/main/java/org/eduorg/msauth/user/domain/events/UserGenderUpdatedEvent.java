package org.eduorg.msauth.user.domain.events;

import org.eduorg.msauth.common.domain.domain_events.DomainEvents;
import org.eduorg.msauth.user.domain.vo.UserGender;
import org.eduorg.msauth.user.domain.vo.UserId;

public class UserGenderUpdatedEvent extends DomainEvents {

    private final UserId userId;
    private final UserGender oldGender;
    private final UserGender newGender;


    public UserGenderUpdatedEvent(UserId userId, UserGender oldGender, UserGender newGender) {
        this.userId = userId;
        this.oldGender = oldGender;
        this.newGender = newGender;
    }


    public UserId getUserId() {
        return userId;
    }

    public UserGender getOldGender() {
        return oldGender;
    }

    public UserGender getNewGender() {
        return newGender;
    }

    public static UserGenderUpdatedEvent create(UserId userId, UserGender oldGender, UserGender newGender) {
        return new UserGenderUpdatedEvent(userId, oldGender, newGender);
    }
}
