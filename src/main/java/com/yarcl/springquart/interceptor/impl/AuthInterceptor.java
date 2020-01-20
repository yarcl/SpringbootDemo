package com.yarcl.springquart.interceptor.impl;

import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiaozhi on 2019/6/26.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IPass methodAnnotation = handlerMethod.getMethodAnnotation(IPass.class);
        return (methodAnnotation != null && methodAnnotation.value().equals(IPass.Auth.PASS)) || validSession(request);
    }

    private Boolean validSession(HttpServletRequest request) {
        return request.getSession() != null;
    }
}
