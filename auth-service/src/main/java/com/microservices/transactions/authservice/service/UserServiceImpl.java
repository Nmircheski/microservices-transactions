package com.microservices.transactions.authservice.service;

import com.microservices.transactions.authservice.dao.PermissionRepository;
import com.microservices.transactions.authservice.dao.RoleRepository;
import com.microservices.transactions.authservice.dao.UserRepository;
import com.microservices.transactions.authservice.model.Permission;
import com.microservices.transactions.authservice.model.Role;
import com.microservices.transactions.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;



    @Override
    @Transactional
    public User save(User user) {
        user.setUsername(user.getUsername());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword("{bcrypt}"+passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setEnabled(true);
        Role r = new Role();
        if(roleRepository.findByName("ROLE_USER")==null) {
            r.setName("ROLE_USER");
            List<Role> roles = new ArrayList<>();
            r.setUsers(Arrays.asList(user));
            roleRepository.save(r);
        }
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
