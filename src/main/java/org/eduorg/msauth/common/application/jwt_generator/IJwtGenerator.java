package org.eduorg.msauth.common.application.jwt_generator;

public interface IJwtGenerator<T> {
    T generate(T param);
}
