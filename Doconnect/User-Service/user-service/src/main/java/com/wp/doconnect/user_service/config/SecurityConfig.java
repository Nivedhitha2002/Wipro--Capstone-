package com.wp.doconnect.user_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/h2-console/**").permitAll() // allow H2 console
            .anyRequest().permitAll()
            .and().headers().frameOptions().disable(); // allow frames for H2 console
        return http.build();
    }
}
