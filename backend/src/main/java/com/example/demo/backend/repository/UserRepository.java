package com.example.demo.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.backend.entity.User;

public interface UserRepository extends CrudRepository<User,String>{
    
     Optional<User> findByEmail(String email);

   
     boolean existsByEmail(String email);
}
