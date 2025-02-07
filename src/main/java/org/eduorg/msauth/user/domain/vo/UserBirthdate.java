package org.eduorg.msauth.user.domain.vo;

import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserBirthdateException;

import java.time.LocalDate;


public class UserBirthdate implements ValueObject<UserBirthdate> {

        private final LocalDate birthdate;

        public UserBirthdate(LocalDate birthdate) {
            if (birthdate == null || birthdate.isAfter( LocalDate.now() ) || birthdate.isBefore( LocalDate.EPOCH ) ) {
                throw new InvalidUserBirthdateException();
            }
            this.birthdate = birthdate;
        }

        public LocalDate getBirthdate() {
            return birthdate;
        }

        @Override
        public boolean isEquals(UserBirthdate otherBirthdate) {
            return this.birthdate.equals(otherBirthdate.getBirthdate());
        }

        public static UserBirthdate create(LocalDate birthdate) {
            return new UserBirthdate(birthdate);
        }
}
