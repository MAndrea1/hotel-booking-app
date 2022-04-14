package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.UserRole;
import com.example.hotelbookingapp.repository.UserRoleRepository;
import com.example.hotelbookingapp.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping({"/userroles"})
    public List<UserRole> getUserRoles() {
        return userRoleService.findAll();
    }
}
