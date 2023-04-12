package com.example.demo.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.backend.entity.Address;
import com.example.demo.backend.entity.AddressRepository;
import com.example.demo.backend.entity.User;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findByUser(User user) {
        return addressRepository.findByUser(user);
    }

    public Address creatAddress(User user, String line1, String line2, String zipcode) {
        Address address = new Address();
        address.setUser(user);
        address.setLine1(line1);
        address.setLine2(line2);
        address.setZipcode(zipcode);
        return addressRepository.save(address);

    }
}
