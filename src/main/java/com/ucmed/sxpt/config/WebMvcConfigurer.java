package com.ucmed.sxpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

// FileName: SpringWebMvcConfigurer
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    // 配置静态资源
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        super.addResourceHandlers(registry);
//    }

    // 配置拦截器
    //addPathPatterns 用于添加拦截规则
    //excludePathPatterns 用于排除拦截规则
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截规则
        // 只有用户登录过后才能访问资源
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/public/**"); // 公用页面
        super.addInterceptors(registry);
    }
}