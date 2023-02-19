package com.project.ecommerceapi.service;

import com.project.ecommerceapi.model.Address;
import com.project.ecommerceapi.repository.AddressRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository repository;

    public void saveAddress(Address address) {
        repository.save(address);
    }

}
