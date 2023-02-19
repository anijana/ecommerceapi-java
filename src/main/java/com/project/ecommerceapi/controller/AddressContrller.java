package com.project.ecommerceapi.controller;

import com.project.ecommerceapi.model.Address;
import com.project.ecommerceapi.model.User;
import com.project.ecommerceapi.repository.UserRepository;
import com.project.ecommerceapi.service.AddressService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("api/v1/address")
public class AddressContrller {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressService service;

    @PostMapping(value = "/add-address")
    public String saveAddress(@RequestBody String addressRequest) {

        JSONObject json = new JSONObject(addressRequest);
        Address address = setAddress(json);
        service.saveAddress(address);

        return "Saved address";

    }

    private Address setAddress(JSONObject json) {

        Address address = new Address();

        address.setAddressId(json.getInt("addressId"));
        address.setAddressName(json.getString("addressName"));
        address.setLandmark(json.getString("landmark"));
        address.setPhoneNumber(json.getString("phoneNumber"));
        address.setZipcode(json.getString("zipcode"));
        address.setState(json.getString("state"));


        String userId = json.getString("userId");
        User user = userRepository.findById(Integer.valueOf(userId)).get();
        address.setUser(user);

        return address;


    }



}
