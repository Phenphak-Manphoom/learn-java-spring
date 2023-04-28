package com.example.demo.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.backend.businessLogic.UserBusiness;
import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.model.LoginRequest;
import com.example.demo.backend.model.Register;
import com.example.demo.backend.model.RegisterResponse;

@RestController
@RequestMapping("/user")
public class UserApi {
    // 1 field injection
    // @Autowired
    // private TestBusiness business;

    // 2 โค้ดเร็วกว่า constructor injection
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws BaseException {
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody Register reqRegister) throws BaseException {

        RegisterResponse response = business.register(reqRegister);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException{
        String refreshToken = business.refreshToken();
        return ResponseEntity.ok(refreshToken);
    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePictur(MultipartFile file) throws BaseException {
        String response = business.uploadProfilePictur(file);
        return ResponseEntity.ok(response);
    }
}

// ResponseEntity เป็นการ custom http