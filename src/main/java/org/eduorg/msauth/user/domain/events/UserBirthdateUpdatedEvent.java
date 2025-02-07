package org.eduorg.msauth.user.domain.events;

import org.eduorg.msauth.common.domain.domain_events.DomainEvents;
import org.eduorg.msauth.user.domain.vo.UserBirthdate;
import org.eduorg.msauth.user.domain.vo.UserId;

public class UserBirthdateUpdatedEvent extends DomainEvents {

    private final UserId userId;
    private final UserBirthdate oldBirthdate;
    private final UserBirthdate newBirthdate;

    private UserBirthdateUpdatedEvent(UserId userId, UserBirthdate oldBirthdate, UserBirthdate newBirthdate) {
        this.userId = userId;
        this.oldBirthdate = oldBirthdate;
        this.newBirthdate = newBirthdate;
    }

    public UserId getUserId() {
        return userId;
    }

    public UserBirthdate getOldBirthdate() {
        return oldBirthdate;
    }

    public UserBirthdate getNewBirthdate() {
        return newBirthdate;
    }

    public static UserBirthdateUpdatedEvent create(UserId userId, UserBirthdate oldBirthdate, UserBirthdate newBirthdate) {
        return new UserBirthdateUpdatedEvent(userId, oldBirthdate, newBirthdate);
    }
}
