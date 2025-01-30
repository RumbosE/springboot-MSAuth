package org.eduorg.msauth.common.application.service;

import org.eduorg.msauth.common.application.service.dto.ApplicationServiceEntryDto;
import org.eduorg.msauth.common.utils.result.Result;

import java.util.concurrent.Future;

public interface IService<T, K> {
    Result<K> execute(T data);

}

