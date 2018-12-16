package com.microservices.transactions.authservice.controller;

import com.microservices.transactions.authservice.model.User;
import com.microservices.transactions.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by nikola on 10/19/18.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users/me")
    @ResponseBody
    public Object currentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }


    @GetMapping("/hi")
    public String hi() {return "HI";}

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public User userRegistration(@RequestBody User user)
    {
            userService.save(user);
            return user;
    }

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }


}
