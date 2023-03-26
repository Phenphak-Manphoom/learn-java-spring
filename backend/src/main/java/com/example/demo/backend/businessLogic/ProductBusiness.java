package com.example.demo.backend.businessLogic;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.exception.ProductException;

@Service
public class ProductBusiness {
    public String getProductById(String id) throws BaseException {
        if (Objects.equals("1234", id)) {
            throw ProductException.notFound();
        }
        return id;

    }
}
