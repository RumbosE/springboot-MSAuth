package org.eduorg.msauth.common.application.aspects.logger;

import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.application.service.dto.ApplicationServiceEntryDto;
import org.eduorg.msauth.common.utils.result.Result;

public class LoggerAspect<T,K> implements IService<T,K> {



    private final IService<T,K> service;


    private final ILogger logger;

    public LoggerAspect(IService<T,K> service, ILogger logger){
        this.service = service;
        this.logger = logger;
    }

    @Override
    public Result<K> execute(T data) {
        Result<K> result = service.execute(data);
        if(result.isSuccess()){
            logger.debug("Service: " + service.getClass().getName() +" executed successfully");
        }else{
            logger.error("Service failed: " + service.getClass().getName(), result.getFailure());
        }
        return result;
    }
}
