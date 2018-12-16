package com.microservices.transactions.authservice.service;

import com.microservices.transactions.authservice.dao.UserRepository;
import com.microservices.transactions.authservice.model.Role;
import com.microservices.transactions.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDetails save(User user) {
        user.setUsername(user.getUsername());

        passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword("{bcrypt}"+passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setEnabled(true);
        List<Role> roles = new ArrayList<>();
        Role r = new Role();
        r.setName("ROLE_USER");
        roles.add(r);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
