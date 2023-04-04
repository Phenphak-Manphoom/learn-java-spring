package com.example.demo.backend.mapper;

import org.mapstruct.Mapper;

import com.example.demo.backend.entity.User;
import com.example.demo.backend.model.RegisterResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegisterResponse response(User user);
}
