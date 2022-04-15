package com.example.hotelbookingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(Principal principal) {
        return "Hello";
    }

    @GetMapping("/api/test")
    public String test2(Principal principal) {
        return "Hello again, " + principal.getName();
    }
}
