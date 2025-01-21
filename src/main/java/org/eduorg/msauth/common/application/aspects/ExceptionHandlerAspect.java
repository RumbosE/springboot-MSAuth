package org.eduorg.msauth.common.application.aspects;

import org.eduorg.msauth.common.application.handlers.IExceptionHandler;
import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.application.service.dto.ApplicationServiceEntryDto;
import org.eduorg.msauth.common.utils.result.Result;

public class ExceptionHandlerAspect<T extends ApplicationServiceEntryDto,K> implements IService<T,K> {
    private final IService<T,K> service;
    private final IExceptionHandler exceptionHandler;

    public ExceptionHandlerAspect( IService<T,K> service, IExceptionHandler exceptionHandler ){
        this.service = service;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public Result<K> execute(T data) {
        try{
            return service.execute(data);
        } catch (Exception e) {
            return Result.makeFailure( exceptionHandler.handleException(e) );
        }
    }
}
