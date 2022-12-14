package com.sgi.springtraining.methodsecurity.application.spring;

import com.sgi.springtraining.methodsecurity.application.web.interceptor.LoggerInterceptor;
import com.sgi.springtraining.methodsecurity.application.web.interceptor.SessionTimerInterceptor;
import com.sgi.springtraining.methodsecurity.application.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        super();
    }

    // API

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/anonymous.html");
        registry.addViewController("/login.html");
        registry.addViewController("/homepage.html");
        registry.addViewController("/console.html");
        registry.addViewController("/csrfHome.html");
        registry.addViewController("/index.html");
        registry.addViewController("/").setViewName("anonymous.html");
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");

        return bean;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
        registry.addInterceptor(new UserInterceptor());
        registry.addInterceptor(new SessionTimerInterceptor());
    }
}