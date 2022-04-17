package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.service.GuestService;
import com.example.hotelbookingapp.service.Imp.GuestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
public class GuestsController {

    @Autowired
    private GuestServiceImp guestService;

    @GetMapping({"/guests"})
    public List<Guest> getGuests() {
        return guestService.findAll();
    }

    @GetMapping("/guest/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public ResponseEntity<?> getGuestById(@PathVariable(value = "userId") String userId, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(guestService.findById(Integer.valueOf(userId)));
    }



}
