package com.example.demo.aspect;

import com.example.demo.log.LogUtil;
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
public class ControllerEntryAspect {

    private ApplicationArguments arguments;

    @Autowired
    private LogUtil log;

    @Autowired
    public void setArguments(ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void controllerEntry() {}

    @Before("controllerEntry()")
    public void beforeWebLog(JoinPoint joinPoint) {
        StringBuilder builder = new StringBuilder();
        log.append("CLASS_METHOD : ").append(joinPoint.getSignature().getDeclaringTypeName()).append(".")
            .append(joinPoint.getSignature().getName()).append("()").endLine();
        log.append("ARGS : ").append(Arrays.toString(joinPoint.getArgs())).endLine();
        log.logInfo();
    }

}
