package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {


    private ApplicationArguments arguments;

    @Autowired
    public void setArguments(ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void webLog() {}

    @Before("webLog()")
    public void beforeWebLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("----------------------------------------------------------------------\n");
        log.info("URL : {} \n", request.getRequestURL().toString());
        log.info("HTTP_METHOD : {}\n", request.getMethod());
        log.info("IP : {}\n", request.getRemoteAddr());
        log.info("CLASS_METHOD : {}.{}\n", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("ARGS : {}\n", Arrays.toString(joinPoint.getArgs()));
        log.info("args: {}\n", arguments.getOptionValues("mode"));
    }
}
