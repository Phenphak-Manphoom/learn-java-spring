package com.example.demo.backend;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.backend.entity.Address;
import com.example.demo.backend.entity.Social;
import com.example.demo.backend.entity.User;
import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.service.AddressService;
import com.example.demo.backend.service.SocialService;
import com.example.demo.backend.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {
    @Autowired
    private UserService userService;

    @Autowired
    private SocialService socialService;

    @Autowired
    private AddressService addressService;

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
    void testCreateSocial() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();
        Social getSocial = user.getSocial();
        Assertions.assertNull(getSocial);
        Social social = socialService.createSocial(user, SocialCreateData.facebook, SocialCreateData.line,
                SocialCreateData.instagram);
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialCreateData.facebook, social.getFacebook());
    }

    @Order(4)
    @Test
    void testCreateAddress() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();
        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());
        createAddress(user, AddressCreateData.line1, AddressCreateData.line2, AddressCreateData.zipcode);
        createAddress(user, AddressCreateData2.line1, AddressCreateData2.line2, AddressCreateData2.zipcode);
    }

    private void createAddress(User user, String line1, String line2, String zipcode) {
        Address address = addressService.creatAddress(user, line1, line2, zipcode);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(line1, address.getLine1());
        Assertions.assertEquals(line2, address.getLine2());
        Assertions.assertEquals(zipcode, address.getZipcode());
    }

    @Order(4)
    @Test
    void testDeleteById() {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();

        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialCreateData.facebook, social.getFacebook());

        List<Address> addresses = user.getAddresses();
        Assertions.assertFalse(addresses.isEmpty());
        Assertions.assertEquals(2, addresses.size());
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

    interface SocialCreateData {
        String facebook = "somsom";
        String line = "@som";
        String instagram = "som@insta";
    }

    interface AddressCreateData {
        String line1 = "11/2";
        String line2 = "Bangkok";
        String zipcode = "123456";
    }

    interface AddressCreateData2 {
        String line1 = "11";
        String line2 = "Bangkok";
        String zipcode = "10310";
    }
}
