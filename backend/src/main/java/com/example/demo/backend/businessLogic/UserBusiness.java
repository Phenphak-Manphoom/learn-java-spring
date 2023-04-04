package com.example.demo.backend.businessLogic;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.backend.entity.User;
import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.exception.FileException;
import com.example.demo.backend.exception.UserException;
import com.example.demo.backend.mapper.UserMapper;
import com.example.demo.backend.model.LoginRequest;
import com.example.demo.backend.model.Register;
import com.example.demo.backend.model.RegisterResponse;
import com.example.demo.backend.service.UserService;

@Service
public class UserBusiness {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public String login(LoginRequest request) throws BaseException {

        Optional<User> findByEmail = userService.findByEmail(request.getEmail());
        if (findByEmail.isEmpty()) {
            throw UserException.loginFailEmailNotFound();
        }

        User user = findByEmail.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword()))

        {
            throw UserException.loginFailPasswordIncorrect();
        }

        String token = "JWT TO DO";
        return token;
    }

    public RegisterResponse register(Register register) throws BaseException {

        User user = userService.createUser(register.getName(), register.getEmail(), register.getPassword());
        return userMapper.response(user);

    }

    public String uploadProfilePictur(MultipartFile file) throws BaseException {
        if (file == null) {
            // throw error
            throw FileException.fileNull();
        }
        if (file.getSize() > 1048576 * 2) {
            // throw error
            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            // throw error
            throw FileException.unsupported();
        }

        List<String> supportTypes = Arrays.asList("image/jpeg", "image/npg");
        if (!supportTypes.contains(contentType)) {
            // throw error
            throw FileException.unsupported();
        }

        // เอา data จากตรงนี้ไปใช้ โดย upload ไปที่ server
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return "";
    }
}

// api มีอะไร business มีอันนั้น