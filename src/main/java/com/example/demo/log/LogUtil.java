package com.example.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogUtil {
    private static final String SEPARATE_LINE =
        "-----------------------------------------------------------------\n";
    private StringBuilder builder;
    private Logger log = LoggerFactory.getLogger(LogUtil.class);

    LogUtil() {
        builder = new StringBuilder();
    }

    public NewLineBuilder newLine() {
        return new NewLineBuilder(this.builder);
    }

    public void logInfo() {
        log.info(SEPARATE_LINE);
        log.info(builder.toString());
    }

    public static class NewLineBuilder {
        private static final String COLON = " : ";
        private StringBuilder stringBuilder;
        private String key;
        private String value;
        NewLineBuilder(StringBuilder stringBuilder) {
            this.stringBuilder = stringBuilder;
        }
        public NewLineBuilder withKey(String key) {
            this.key = key;
            return this;
        }
        public NewLineBuilder withValue(String value) {
            this.value = value;
            return this;
        }
        public void endLine() {
            stringBuilder.append(key).append(COLON).append(value).append("\n");
        }
    }
}
