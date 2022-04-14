package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @Test
    public void printGuestRepositoryTypeAll() {
        List<Guest> guestList = guestRepository.findAll();

        System.out.println("Guests = " + guestList);
    }

}