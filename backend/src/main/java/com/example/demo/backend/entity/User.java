package com.example.demo.backend.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
public class User extends BaseEntity {

  @Column(nullable = false, length = 120)
  private String name;
  @Column(nullable = false, unique = true, length = 60)
  private String email;

  @Column(nullable = false, length = 120)
  private String password;

  @OneToOne(mappedBy = "user", orphanRemoval = true)
  private Social social;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Address> addresses;
}
