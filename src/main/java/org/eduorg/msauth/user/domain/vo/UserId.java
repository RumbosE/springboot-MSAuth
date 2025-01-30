package org.eduorg.msauth.user.domain.vo;

import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserIdException;

import java.util.UUID;

public class UserId implements ValueObject<UserId> {

        private final UUID id;

        private UserId(UUID userId) {
            if( userId == null ) {
                throw new InvalidUserIdException();
            }
            this.id = userId;
        }

        public UUID getId() {
            return id;
        }

        @Override
        public boolean isEquals(UserId valueObject) {
            return this.id.equals(valueObject.getId());
        }

        public static UserId create(UUID userId) {
            return new UserId(userId);
        }

}
