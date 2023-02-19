package com.project.ecommerceapi.controller;

import com.project.ecommerceapi.model.User;
import com.project.ecommerceapi.service.UserService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserContrller {
    @Autowired
    UserService service;

    @PostMapping(value = "/add-user")
    public ResponseEntity<String> saveUser(@RequestBody String requestUser) {

        JSONObject json = new JSONObject(requestUser);

        List<String> validationList = validateDoctor(json);

        if (validationList.isEmpty()) {
            User user = setUser(json);
            service.saveUser(user);
            return new ResponseEntity<>("User saved", HttpStatus.CREATED);
        } else {

            String[] answer = Arrays.copyOf(
                    validationList.toArray(), validationList.size(), String[].class);

            return new ResponseEntity<>("Please pass these mandatory parameters- " +
                    Arrays.toString(answer), HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(value = "/userid")
    public List<User> getDoctor(@Nullable @RequestParam String userId) {

        return service.getUser(userId);
    }


    private List<String> validateDoctor(JSONObject json) {

        List<String> errorList = new ArrayList<>();

        if (!json.has("userId")) {
            errorList.add("userId");
        }

        if (!json.has("userName")) {
            errorList.add("userName");
        }


        if (!json.has("email")) {
            errorList.add("email");
        }
        if (!json.has("password")) {
            errorList.add("password");
        }
        return errorList;

    }


    public User setUser(JSONObject json) {
        User user = new User();

        String userId = json.getString("userId");
        user.setUserId(Integer.valueOf(userId));

        String userName = json.getString("userName");
        user.setUserName(userName);

        String email = json.getString("email");
        user.setEmail(email);

        String password = json.getString("password");
        user.setPassword(password);

        if (json.has("phoneNumber")) {
            String phone = json.getString("phoneNumber");
            user.setPhoneNumber(phone);
        }

        return user;
    }
}
