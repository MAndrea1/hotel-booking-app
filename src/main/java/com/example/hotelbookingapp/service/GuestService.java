package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<Guest> findAll();
    Optional<Guest> findByUserId(Integer userId);
    Optional<Guest> findById(Integer userId);
    Boolean existsById(Integer userId);
    Guest save(Guest guest) throws Exception;

}
