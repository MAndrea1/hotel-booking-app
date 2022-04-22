package com.example.hotelbookingapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
public class TestController {

    @GetMapping("/test")
    public String test(Principal principal) {
        if (principal != null) {
            return principal.toString();
        }
        return principal.toString() + "No user logged in";
    }

    @GetMapping("/api/test")
    public String test2(Principal principal) {
        return "Hello again, " + principal.getName();
    }
}
