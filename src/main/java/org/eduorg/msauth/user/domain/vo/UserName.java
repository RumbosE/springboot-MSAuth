package org.eduorg.msauth.user.domain.vo;

import org.eduorg.msauth.common.domain.value_object.ValueObject;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserLastNameException;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserNameLengthException;

public class UserName implements ValueObject<UserName> {
    String name;
    String lastName;

    private UserName( String name, String lastName ) {

        if( name.length() < 4 || name == null || name.isEmpty() ) {
            throw new InvalidUserNameLengthException();
        }

        if( lastName != null && lastName.length() < 4 ) {
            throw new InvalidUserLastNameException();
        }

        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastName;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    @Override
    public boolean isEquals(UserName valueObject) {
        return this.name.equals(valueObject.getName()) && this.lastName.equals(valueObject.getLastname());
    }

    public static UserName create(String name, String lastName)  {
        return new UserName(name, lastName);
    }
}
