package org.eduorg.msauth.user.domain.vo;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserEmailException;

public class UserEmail implements ValueObject<UserEmail> {

    private final String email;

    private UserEmail(String email) {
        if ( !isValid(email) ) {
            throw new InvalidUserEmailException();
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private boolean isValid(String email) {

        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean isEquals(UserEmail valueObject) {
        return this.email.equals(valueObject.getEmail());
    }

    public static UserEmail create(String email) {
        return new UserEmail(email);
    }
}
