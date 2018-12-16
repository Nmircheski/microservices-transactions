package com.microservices.transactions.authservice.service;

import com.microservices.transactions.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDetails save(User user);
    List<User> getAllUsers();
}
