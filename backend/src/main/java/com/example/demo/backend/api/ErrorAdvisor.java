package com.example.demo.backend.api;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.backend.exception.BaseException;

import lombok.Data;

@ControllerAdvice // จัดการ error
public class ErrorAdvisor { // มันจะไปหาว่ามี Error ที่ชื่อ BaseException ไหม ถ้ามีก็จะ handle แล้วเปลี่ยน
                            // obj ที่จะ return ออกไป เป็นในแบบที่เรา custom ใน class ErrorResponse
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setError(exception.getMessage()); // เอามาจาก message ใน BasException
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @Data
    public static class ErrorResponse {
        private LocalDateTime timestamp = LocalDateTime.now();
        private int status;
        private String error;
    }
}
