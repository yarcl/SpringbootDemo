package com.yarcl.springquart.interceptor;

import com.yarcl.springquart.interceptor.config.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhi on 2019/6/26.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> filterList = new ArrayList<>();
        filterList.add("/");
        filterList.add("/**.do");
        filterList.add("/**.servlet");
        filterList.add("/*");
        filterList.add("/*/*");
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns(filterList);
    }

}