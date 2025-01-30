package org.eduorg.msauth.common.application.jwt_generator;

public interface IJwtGenerator<T,K> {
    K generate(T param);
}
