package com.example.demo.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.backend.businessLogic.TestBusiness;
import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.model.Register;
import com.example.demo.backend.model.TestResponse;

@RestController
@RequestMapping("/test")
public class TestApi {
    // 1 field injection
    // @Autowired
    // private TestBusiness business;

    // 2 โค้ดเร็วกว่า constructor injection
    private final TestBusiness business;

    public TestApi(TestBusiness business) {
        this.business = business;
    }

    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setName("naja");
        response.setFood("tomyam kung");
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Register reqRegister) throws BaseException {

        String response = business.register(reqRegister);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<String>uploadProfilePictur(MultipartFile file) throws BaseException{
       String response = business.uploadProfilePictur(file);
       return ResponseEntity.ok(response);
    }
}

// ResponseEntity เป็นการ custom http