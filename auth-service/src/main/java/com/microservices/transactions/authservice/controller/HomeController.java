package com.microservices.transactions.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by nikola on 10/19/18.
 */
@RestController
public class HomeController {

    @GetMapping("/user")
    public Principal user(Principal principal)
    {
        return principal;
    }

    @RequestMapping("/home")
    public String hello()
    {
        return "HEY";
    }

    @GetMapping("/hi")
    public String hi() {return "HI";}

}
