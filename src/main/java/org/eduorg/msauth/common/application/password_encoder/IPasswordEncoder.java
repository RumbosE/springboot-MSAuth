package org.eduorg.msauth.common.application.password_encoder;

public interface IPasswordEncoder {

        String encode(String password);

        boolean matches(String password, String encodedPassword);
}
