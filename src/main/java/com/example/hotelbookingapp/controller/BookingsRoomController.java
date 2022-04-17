package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.BookingsRoom;
import com.example.hotelbookingapp.service.BookingsRoomService;
import com.example.hotelbookingapp.service.Imp.BookingsRoomServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingsRoomController {

    @Autowired
    private BookingsRoomServiceImp bookingsRoomService;

    @GetMapping({"/bookingsroom"})

    public List<BookingsRoom> getBooking() {
        return bookingsRoomService.findAll();
    }
}
