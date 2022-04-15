package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.repository.UserRepository;
import com.example.hotelbookingapp.service.Imp.UserServiceImp;
import com.example.hotelbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('SUPERADMIN')")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    Authentication authentication;

    @GetMapping({"/allusers"})
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping({"/users"})
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getPlainUsers() {
        return userService.findAllByRole(3);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("#userId == principal.getName()")
    public String getEmployeesById(@PathVariable(value = "userId") Integer userId, Principal principal) {
        return "content" + userId + " " + principal.getName();
    }

//    @GetMapping("/user/{id}")
//    public String getEmployeesById(@PathVariable(value = "id") String id, Principal principal) {
//        if (userService.findByUserEmail(principal.getName()).get().getId().toString().equals(id)) {
//            return "correct: mail " + userService.findByUserEmail(principal.getName()).get().getId() + " and " + id + " are equal";
//        }
//        return "incorrect: mail " + userService.findByUserEmail(principal.getName()).get().getId() + " and " + id + " are not equal";
//    }
}
