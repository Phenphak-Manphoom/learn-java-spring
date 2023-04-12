package com.example.demo.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.backend.entity.Social;
import com.example.demo.backend.entity.User;

public interface SocialRepository extends CrudRepository<Social,String> {
    
    Optional<Social> findByUser(User user);
}
