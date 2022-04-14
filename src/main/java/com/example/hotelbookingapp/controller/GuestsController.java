package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestsController {

    @Autowired
    private GuestService guestService;

    @GetMapping({"/guests"})
    public List<Guest> getFacilities() {
        return guestService.findAll();
    }
}
