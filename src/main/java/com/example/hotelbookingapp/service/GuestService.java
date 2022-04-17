package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestService extends BaseService<Guest, Guest>{
    Optional<Guest> findByUserId(Integer userId);
    Boolean existsById(Integer userId);
}
