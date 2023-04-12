package com.example.demo.backend.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {

  List<Address> findByUser(User user);

}
