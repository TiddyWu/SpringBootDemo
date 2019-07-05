package com.example.demo.interceptor;

import com.example.demo.log.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    private LogUtil log;

    @Autowired
    public void setLog(LogUtil log) {
        this.log = log;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.newLine().withKey("QUERY_STRING").withValue(request.getQueryString()).endLine();
        log.newLine().withKey("SESSION").withValue(request.getSession().toString()).endLine();
        log.newLine().withKey("PATH_INFO").withValue(request.getPathInfo()).endLine();
        log.newLine().withKey("URL").withValue(request.getRequestURL().toString()).endLine();
        log.newLine().withKey("HTTP_METHOD").withValue(request.getMethod()).endLine();
        log.newLine().withKey("IP").withValue(request.getRemoteAddr()).endLine();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.newLine().withKey(cookie.getName()).withValue(cookie.getValue()).endLine();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable
        ModelAndView modelAndView) throws Exception {
    }

}
