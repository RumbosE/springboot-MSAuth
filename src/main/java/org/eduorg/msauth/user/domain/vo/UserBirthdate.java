package org.eduorg.msauth.user.domain.vo;

import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserBirthdateException;

import java.util.Date;

public class UserBirthdate implements ValueObject<UserBirthdate> {

        private final Date birthdate;

        public UserBirthdate(Date birthdate) {
            if (birthdate == null || birthdate.after(new Date()) || birthdate.before(new Date(0)) ) {
                throw new InvalidUserBirthdateException();
            }
            this.birthdate = birthdate;
        }

        public Date getBirthdate() {
            return birthdate;
        }

        @Override
        public boolean isEquals(UserBirthdate otherBirthdate) {
            return this.birthdate.equals(otherBirthdate.getBirthdate());
        }

        public static UserBirthdate create(Date birthdate) {
            return new UserBirthdate(birthdate);
        }
}
