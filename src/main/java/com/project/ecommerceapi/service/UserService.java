package com.project.ecommerceapi.service;

import com.project.ecommerceapi.model.User;
import com.project.ecommerceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User saveUser(User user) {
        String username = user.getUserName();
        username = "Mr./Mrs/Miss " + username;
        user.setUserName(username);
        return repository.save(user);
    }

    public List<User> getUser(String userId) {

        List<User> userList;

        if(null != userId) {
            userList = new ArrayList<>();
            userList.add(repository.findById(Integer.valueOf(userId)).get());
        } else {
            userList = repository.findAll();
        }
        return userList;
    }

    public User getUserById(String userId) {

        return repository.findById(Integer.valueOf(userId)).get();
    }
}
