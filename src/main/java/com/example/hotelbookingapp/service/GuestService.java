package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.dto.UpdateGuestDto;
import com.example.hotelbookingapp.model.Guest;

import java.util.Optional;

public interface GuestService extends BaseService<Guest, UpdateGuestDto>{
    Optional<Guest> findByUserId(Integer userId);
    Boolean existsById(Integer userId);
    Boolean existByEmail(String email);

    Guest save(Guest guest) throws Exception;
    Guest updateByUserId(Integer userId, UpdateGuestDto updateGuestDto) throws Exception;
}
