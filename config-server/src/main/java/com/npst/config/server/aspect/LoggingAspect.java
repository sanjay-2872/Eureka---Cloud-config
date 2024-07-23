package com.npst.config.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(com.npst.config.server.annotations.AuditLog)")
    public Object beforeMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        try{
            log.info("Before executing: {}, Request : {}" , joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
            Object result = joinPoint.proceed();
            log.info("After executing: {}, Response : {}" , joinPoint.getSignature(), result.toString());
            return result;
        } catch (Throwable e){
            log.error("Exception Message :: {}, Exception Class :: {}, Exception Stack trace : {}",e.getMessage(),e.getClass(),e.getStackTrace());
            throw e;
        }

    }
}

