package com.tyss.image_application.aspect;

import com.tyss.image_application.entity.Image;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {

    @Pointcut("execution(* com.tyss.image_application.service.*.*(..))")
    public void loggingPointCut() {

    }

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before method invoke" + joinPoint.getSignature());
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("After method invoke" + joinPoint.getSignature());
    }

    @AfterReturning(value = "execution(* com.tyss.image_application.service.*.*(..))", returning = "image")
    public void after(JoinPoint joinPoint, Image image) {
        log.info("After method invoke" + image);
    }

    @AfterThrowing(value = "execution(* com.tyss.image_application.service.*.*(..))", throwing = "NotFound")
    public void after(JoinPoint joinPoint, Exception ex) {
        log.info("After method invoke" + ex.getMessage());
    }

    @Around("loggingPointCut()")
    public Object after(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Before method invoke" + joinPoint.getSignature());
        Object object = joinPoint.proceed();
        if (object instanceof Image) {
            log.info("After method invoked" + joinPoint.getSignature());
        }

        return object;
    }
}
