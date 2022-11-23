package com.sgi.springtraining.rest.config;

import com.sgi.springtraining.rest.filter.DoNothingFilter;
import com.sgi.springtraining.rest.filter.HeaderFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/","/api/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAfter(
                        new DoNothingFilter(), BasicAuthenticationFilter.class
                    );

        http.csrf().disable();

        return http.build();
    }

    @Bean
    public FilterRegistrationBean headerFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(headerFilter());
        registration.addUrlPatterns("/api/*");
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("headerFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "headerFilter")
    public Filter headerFilter() {
        return new HeaderFilter();
    }
}