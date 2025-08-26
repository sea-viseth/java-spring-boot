package org.springclass.springclassproject.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingFilter {
//    @Before("execution(* org.springclass.springclassproject.controller.*.*(..))")
//    public void logBeforeController(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//        log.info("[LOG] Running: {} with args {}", methodName, java.util.Arrays.toString(args));
//    }

    @Pointcut("execution(* org.springclass.springclassproject.service.*.*(..))")
    public void serviceMethods() {
    }

    @Before("serviceMethods()")
    public void logBefore() {
        log.info("📌 [LOG] Method is about to run...");
    }

    @After("serviceMethods()")
    public void logAfter() {
        log.info("✅ [LOG] Method execution finished.");
    }

    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        log.info("▶️ Starting: {}", joinPoint.getSignature());
        // Run the actual method
        Object result = joinPoint.proceed();

        long elapsed = System.currentTimeMillis() - start;

        log.info("⏱ Finished: {} in {} ms", joinPoint.getSignature(), elapsed);

        return result; // must return the original result
    }
}
