package com.example.demo.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class RequestContext {

    @Bean(name = "serviceLog")
    @Scope("request")
    public Logger getRequestSeriviceLog() {
        return LoggerFactory.getLogger(RequestContext.class);
    }
}
