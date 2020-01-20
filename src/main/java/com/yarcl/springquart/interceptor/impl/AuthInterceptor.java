package com.yarcl.springquart.interceptor.impl;

import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Shares on 2019/6/26.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IPass methodAnnotation = handlerMethod.getMethodAnnotation(IPass.class);
        Boolean success = (methodAnnotation != null && methodAnnotation.value().equals(IPass.Auth.PASS)) || validSession(request);
        if(success)
            return true;
        else
            response.sendRedirect("/login.html");
        return false;
    }

    private Boolean validSession(HttpServletRequest request) {
        return request.getSession() != null;
    }
}
