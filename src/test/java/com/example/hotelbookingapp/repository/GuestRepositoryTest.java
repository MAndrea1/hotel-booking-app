package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.model.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @Test
    public void printGuestRepositoryTypeAll() {
        List<Guest> guestList = guestRepository.findAll();

        System.out.println("Guests = " + guestList);
    }

    @Test
    public void printGuestRepositoryById() {
        Optional<Guest> guestList = guestRepository.findById(2);

        System.out.println("Guests = " + guestList);
    }

    @Test
    public void printExistsByFkUserId() {
        Integer idToTest = 28;

        System.out.println("exists: " + guestRepository.existsByFkUserId(idToTest));
        if (guestRepository.existsByFkUserId(idToTest)){
            Guest guest = guestRepository.findByUserId(idToTest).get();
            System.out.println("guest: " + guest);
            Integer guestId = guest.getId();
            System.out.println("guest id: " + guestId);
            System.out.println("guest: " + guestRepository.findById(guestId));
        }
    }



}