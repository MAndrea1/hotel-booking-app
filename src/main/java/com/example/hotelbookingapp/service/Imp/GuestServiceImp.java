package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.repository.GuestRepository;
import com.example.hotelbookingapp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImp implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }
}
