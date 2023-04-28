package com.example.demo.backend.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.backend.entity.User;
import com.example.demo.backend.exception.BaseException;
import com.example.demo.backend.exception.UserException;
import com.example.demo.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(String name, String email, String password) throws BaseException {

        if (Objects.isNull(name)) {
            throw UserException.createNameNull();
        }
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }

        if (Objects.isNull(password)) {
            throw UserException.createPasswordNull();
        }
        if (userRepository.existsByEmail(email)) {
            throw UserException.createEmailDuplicated();
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }
    public User updateName(String id,String name) throws BaseException{
      Optional<User> opt = userRepository.findById(id);
      if (opt.isEmpty()) {
        throw UserException.notFound();
      }
      User user = opt.get();
      user.setName(name);

      return userRepository.save(user);
    }

    public void delete(String id){
        userRepository.deleteById(id);;
      
    }
}
