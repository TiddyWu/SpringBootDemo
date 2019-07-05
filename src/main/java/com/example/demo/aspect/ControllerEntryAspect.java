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
    private static final String KEY_CLASS_METHOD = "CLASS_METHOD";
    private static final String KEY_ARGS = "ARGS";
    private ApplicationArguments arguments;
    private LogUtil log;

    @Autowired
    public void setLog(LogUtil log) {
        this.log = log;
    }

    @Autowired
    public void setArguments(ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void controllerEntry() {}

    @Before("controllerEntry()")
    public void beforeWebLog(JoinPoint joinPoint) {
        StringBuilder builder = new StringBuilder();
        log.newLine().withKey(KEY_CLASS_METHOD).withValue(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()).endLine();
        log.newLine().withKey(KEY_ARGS).withValue(Arrays.toString(joinPoint.getArgs())).endLine();
        log.logInfo();
    }

}
