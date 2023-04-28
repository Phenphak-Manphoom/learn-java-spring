package com.example.demo.backend.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    private SecurityUtil(){}

    public static Optional<String> getCurrentUserId(){
        SecurityContext context = SecurityContextHolder.getContext();
        if (context==null) {
            return Optional.empty();
        }
        Authentication authentication = context.getAuthentication();
        if (authentication==null) {
            return Optional.empty();
        }
         Object principal = authentication.getPrincipal();
         if (principal == null) {
            return Optional.empty();
         }
         String userId=(String) principal;
         return Optional.of(userId);
    }
}
