package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping({"/bookings"})
    public List<Booking> getBooking() {
        return bookingService.findAll();
    }
}
