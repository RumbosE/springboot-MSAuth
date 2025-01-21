package org.eduorg.msauth.user.domain.vo;

import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserIdException;

public class UserId implements ValueObject<UserId> {

        private final String id;

        private UserId(String userId) {
            if(userId == null || userId.length() < 4) {
                throw new InvalidUserIdException();
            }
            this.id = userId;
        }

        public String getId() {
            return id;
        }

        @Override
        public boolean isEquals(UserId valueObject) {
            return this.id.equals(valueObject.getId());
        }

        public static UserId create(String userId) {
            return new UserId(userId);
        }

}
