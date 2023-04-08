package com.example.demo.backend;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.backend.entity.User;
import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {
    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    void testCreate() throws BaseException {
        User user = userService.createUser(
                TestData.name,
                TestData.email,
                TestData.password);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        Assertions.assertEquals(TestData.name, user.getName());
        Assertions.assertEquals(TestData.email, user.getEmail());
        boolean isMatched = userService.matchPassword(TestData.password, user.getPassword());
        Assertions.assertTrue(isMatched);
    }

    @Order(2)
    @Test
    void testUpdate() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();
        User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());

    }

    @Order(3)
    @Test
    void testDeleteById() {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();
        userService.delete(user.getId());

        Optional<User> optDelete = userService.findByEmail(TestData.email);
        Assertions.assertTrue(optDelete.isEmpty());

    }

    interface TestData {
        String name = "som";
        String email = "som@test.com";
        String password = "123456";
    }

    interface TestUpdateData {
        String name = "sommai";

    }
}
