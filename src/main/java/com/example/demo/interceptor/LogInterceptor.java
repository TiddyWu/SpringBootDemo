package com.example.demo.interceptor;

import com.example.demo.log.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LogUtil log;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.append("QUERY_STRING : ").append(request.getQueryString()).endLine();
        log.append("SESSION : ").append(request.getSession().toString()).endLine();
        log.append("PATH_INFO : ").append(request.getPathInfo()).endLine();
        log.append("URL : ").append(request.getRequestURL().toString()).endLine();
        log.append("HTTP_METHOD : ").append(request.getMethod()).endLine();
        log.append("IP : ").append(request.getRemoteAddr()).endLine();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable
        ModelAndView modelAndView) throws Exception {
    }

}
