package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findByUserEmail(String userEmail);
}
