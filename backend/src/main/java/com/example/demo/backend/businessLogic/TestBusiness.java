package com.example.demo.backend.businessLogic;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.exception.UserException;
import com.example.demo.backend.model.Register;

@Service
public class TestBusiness {
    public String register(Register register) throws BaseException {

        if (register == null) {
            throw UserException.requestNull();
        }
        // validate email
        if (Objects.isNull(register.getEmail())) {
            throw UserException.emailNull();
        }
        register.getEmail();
        return "";
    }
}

// api มีอะไร business มีอันนั้น