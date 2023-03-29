package com.example.demo.backend.businessLogic;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.exception.FileException;
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

    public String uploadProfilePictur(MultipartFile file) throws BaseException{
        if (file == null) {
            //throw error
            throw FileException.fileNull();
        }
        if (file.getSize() > 1048576 * 2) {
            //throw error
            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
             //throw error
             throw FileException.unsupported();
        }

        List<String> supportTypes=Arrays.asList("image/jpeg","image/npg");
        if (!supportTypes.contains(contentType)) {
             //throw error
             throw FileException.unsupported();
        }

        //เอา data จากตรงนี้ไปใช้ โดย upload ไปที่ server
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
          
            e.printStackTrace();
        }
        return "";
    }
}

// api มีอะไร business มีอันนั้น