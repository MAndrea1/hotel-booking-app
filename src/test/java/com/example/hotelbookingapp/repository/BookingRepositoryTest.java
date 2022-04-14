package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void printGuestRepositoryTypeAll() {
        List<Booking> bookingList = bookingRepository.findAll();

        System.out.println("Booking = " + bookingList);
    }

}