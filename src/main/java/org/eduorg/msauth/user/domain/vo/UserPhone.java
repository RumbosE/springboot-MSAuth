package org.eduorg.msauth.user.domain.vo;

import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserPhoneException;

import java.util.regex.Pattern;

public class UserPhone implements ValueObject<UserPhone> {

    private final String code;
    private final String phone;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{1,5}-[0-9]{6,10}$");

    private UserPhone(String code, String phone) {

        if( code == null || code.isEmpty() || code.length() >5 ) {
            throw new  InvalidUserPhoneException(code + "-" + phone);
        }
        if(phone == null || phone.length() < 6 || phone.length() > 10) {
            throw new  InvalidUserPhoneException(code + "-" + phone);
        }
        this.code = code;
        this.phone = phone;
    }

    public String getFullPhone() {
        return code + '-' + phone;
    }

    public String getPhone() {
        return code + '-' + phone;
    }

    public String getPhoneCode(){
        return code;
    }

    @Override
    public boolean isEquals(UserPhone valueObject) {
        return this.code.equals(valueObject.code) && this.phone.equals(valueObject.phone);
    }

    public static UserPhone create(String code, String phone) {
        return new UserPhone(code, phone);
    }
}
