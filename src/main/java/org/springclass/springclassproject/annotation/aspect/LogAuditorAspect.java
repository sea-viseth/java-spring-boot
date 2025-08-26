package org.springclass.springclassproject.annotation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springclass.springclassproject.annotation.LogAuditor;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAuditorAspect {

    @Around("@annotation(logAuditor)")
    public Object auditMethod(ProceedingJoinPoint joinPoint, LogAuditor logAuditor) throws Throwable {
        String traceId = MDC.get("traceId");

        long start = System.currentTimeMillis();
        log.info("Starting method: {}", joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - start;
        log.info("Method {} executed in {} ms", joinPoint.getSignature(), duration);
        return result;
    }
}
