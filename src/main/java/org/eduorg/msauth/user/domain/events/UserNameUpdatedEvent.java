package org.eduorg.msauth.user.domain.events;

import org.eduorg.msauth.common.domain.domain_events.DomainEvents;
import org.eduorg.msauth.user.domain.vo.UserId;
import org.eduorg.msauth.user.domain.vo.UserName;

public class UserNameUpdatedEvent extends DomainEvents {

        private final UserId userId;
        private final UserName oldUserName;
        private final UserName newUserName;

        private UserNameUpdatedEvent(UserId userId, UserName oldUserName, UserName newUserName) {
            this.userId = userId;
            this.oldUserName = oldUserName;
            this.newUserName = newUserName;
        }

        public UserId getUserId() {
            return userId;
        }

        public UserName getOldUserName() {
            return oldUserName;
        }

        public UserName getNewUserName() {
            return newUserName;
        }

        public static UserNameUpdatedEvent create(UserId userId, UserName oldUserName, UserName newUserName) {
            return new UserNameUpdatedEvent(userId, oldUserName, newUserName);
        }
}
