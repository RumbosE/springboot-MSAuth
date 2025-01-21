package org.eduorg.msauth.user.domain.events;

import org.eduorg.msauth.common.domain.domain_events.DomainEvents;
import org.eduorg.msauth.user.domain.vo.UserId;
import org.eduorg.msauth.user.domain.vo.UserPhone;

public class UserPhoneUpdatedEvent extends DomainEvents {

    private UserId userId;
    private UserPhone oldPhone;
    private UserPhone newPhone;

    private UserPhoneUpdatedEvent(UserId userId, UserPhone oldPhone, UserPhone newPhone) {
        this.userId = userId;
        this.oldPhone = oldPhone;
        this.newPhone = newPhone;
    }

    public UserPhone getOldPhone() {
        return oldPhone;
    }

    public UserPhone getNewPhone() {
        return newPhone;
    }

    public static UserPhoneUpdatedEvent create(UserId userId, UserPhone oldPhone, UserPhone newPhone) {
        return new UserPhoneUpdatedEvent(userId, oldPhone, newPhone);
    }
}
