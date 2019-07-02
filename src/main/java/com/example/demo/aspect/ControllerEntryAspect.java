package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerEntryAspect {


    private ApplicationArguments arguments;

    @Autowired
    public void setArguments(ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void controllerEntry() {}

    @Before("controllerEntry()")
    public void beforeWebLog(JoinPoint joinPoint) {
        log.info("CLASS_METHOD : {}.{}()", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
    }
}
