package com.example.demo.backend.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
