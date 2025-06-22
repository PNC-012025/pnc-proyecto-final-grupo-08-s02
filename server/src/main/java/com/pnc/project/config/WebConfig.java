package com.pnc.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebConfig {

    @Bean
    public HttpMessageConverter<Object> CustomJsonConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    public PasswordEncoder Encoder() {
        return new BCryptPasswordEncoder();
    }
}
