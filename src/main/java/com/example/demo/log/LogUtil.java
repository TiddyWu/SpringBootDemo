package com.example.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogUtil {

    private StringBuilder builder;
    private Logger log = LoggerFactory.getLogger(LogUtil.class);

    LogUtil() {
        builder = new StringBuilder();
    }

    public LogUtil append(String str) {
        builder.append(str);
        return this;
    }

    public LogUtil endLine() {
        builder.append("\n");
        return this;
    }

    public void logInfo() {
        log.info("------------------------------------------------------\n");
        log.info(builder.toString());
    }
}
