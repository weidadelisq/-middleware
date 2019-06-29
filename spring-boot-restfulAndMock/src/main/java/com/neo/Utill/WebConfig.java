package com.neo.Utill;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiSignInterceptor()).addPathPatterns("/1/people/**").excludePathPatterns("/2/people/**");
        super.addInterceptors(registry);
    }
}