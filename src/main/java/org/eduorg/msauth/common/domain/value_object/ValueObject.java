package org.eduorg.msauth.common.domain.value_object;

public interface ValueObject<T> {

    boolean isEquals(T valueObject);
}

