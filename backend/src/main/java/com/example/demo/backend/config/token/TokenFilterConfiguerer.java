package com.example.demo.backend.config.token;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.backend.service.TokenService;

public class TokenFilterConfiguerer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain ,HttpSecurity> {
    private final TokenService tokenService;


    public TokenFilterConfiguerer(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        TokenFilter tokenFilter=new TokenFilter(tokenService);
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
