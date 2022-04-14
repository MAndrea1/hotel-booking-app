package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.BookingsRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingsRoomRepositoryTest {

    @Autowired
    private BookingsRoomRepository bookingsRoomRepository;

    @Test
    public void printBookingsRoomRepositoryTypeAll() {
        List<BookingsRoom> guestList = bookingsRoomRepository.findAll();

        System.out.println("BookingsRooms = " + guestList);
    }

}