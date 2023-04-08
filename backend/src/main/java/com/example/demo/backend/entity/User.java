package com.example.demo.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
public class User extends BassEntity {

  @Column(nullable = false, length = 120)
  private String name;
  @Column(nullable = false, unique = true, length = 60)
  private String email;

  @Column(nullable = false, length = 120)
  private String password;
}
