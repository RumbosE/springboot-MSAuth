package org.eduorg.msauth.user.domain.vo;

import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserPhoneException;

import java.util.regex.Pattern;

public class UserPhone implements ValueObject<UserPhone> {

    private final String code;
    private final String number;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{1,5}-[0-9]{6,10}$");

    private UserPhone(String code, String number) {

        if( code == null || code.isEmpty() || code.length() > 5 ) {
            throw new  InvalidUserPhoneException(code + "-" + number);
        }
        if(number == null || number.length() < 6 || number.length() > 16) {
            throw new  InvalidUserPhoneException(code + "-" + number);
        }
        this.code = code;
        this.number = number;
    }

    public String getFullPhone() {
        return code + '-' + number;
    }

    public String getPhoneNumber() {
        return number;
    }

    public String getPhoneCode(){
        return code;
    }

    @Override
    public boolean isEquals(UserPhone valueObject) {
        return this.code.equals(valueObject.code) && this.number.equals(valueObject.number);
    }

    public static UserPhone create(String code, String number) {
        return new UserPhone(code, number);
    }
}
