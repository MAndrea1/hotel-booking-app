package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.repository.UserRepository;
import com.example.hotelbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('SUPERADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/allusers"})
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping({"/users"})
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getPlainUsers() {
        return userService.findAllByRole(3);
    }
}
