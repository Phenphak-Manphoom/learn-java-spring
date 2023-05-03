package com.example.demo.backend.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.backend.config.token.TokenFilterConfiguerer;
import com.example.demo.backend.service.TokenService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   
    private final TokenService tokenService;

    public SecurityConfig( TokenService tokenService) {
      
        this.tokenService = tokenService;
    }

   

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
             AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests().requestMatchers("/user/register", "/user/login","/actuator/**")
                .anonymous().anyRequest().authenticated()
                .and().apply(new TokenFilterConfiguerer(tokenService));
        return http.build();
    }


    

}