package com.example.demo.backend;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.backend.businessLogic.EmailBusiness;
import com.example.demo.backend.exception.BaseException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestEmailBusiness {
    @Autowired
    private EmailBusiness emailBusiness;

    @Order(1)
    @Test
    void testSendActivateEmail() throws BaseException {
        emailBusiness.sendActivateUserEmail(TestData.email, TestData.name, TestData.token);
    }

    interface TestData {
        String email = "phenphak.forlearn@gmail.com";
        String name = "Tae";
        String token = "jhjhduewhjewiiujnjn3ji98ur40";

    }

}
