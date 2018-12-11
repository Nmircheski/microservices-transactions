package com.microservices.transactions.authservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nikola on 10/19/18.
 */
@RestController
public class UserController {

    @GetMapping("/username")
    @ResponseBody
    public Object currentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }


    @GetMapping("/hi")
    public String hi() {return "HI";}

}
