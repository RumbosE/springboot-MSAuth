package org.eduorg.msauth.common.application.aspects;

import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.application.service.dto.ApplicationServiceEntryDto;
import org.eduorg.msauth.common.utils.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect<T extends ApplicationServiceEntryDto,K> implements IService<T ,K> {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class.getName());


    private final IService<T,K> service;

    public LoggerAspect(IService<T,K> service){
        this.service = service;
    }

    @Override
    public Result<K> execute(T data) {
        try {
            LOG.debug("Log app service: {}", service.getClass().getName());
            return service.execute(data);
        } catch (Exception e) {
            LOG.error("Error in service: {}", service.getClass().getName(), e);
            return Result.makeFailure(e);
        }
    }
}
