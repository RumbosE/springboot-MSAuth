package org.eduorg.msauth.common.application.mapper;
import java.util.concurrent.Future;

public interface IMapper<T,K> {

    K fromDomainToPersistence(T domain);

    T fromPersistenceToDomain(K persistence);
}
