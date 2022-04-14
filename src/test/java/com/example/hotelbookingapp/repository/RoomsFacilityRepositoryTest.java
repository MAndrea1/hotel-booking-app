package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.RoomsFacility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomsFacilityRepositoryTest {

    @Autowired
    private RoomsFacilityRepository roomsFacilityRepository;

    @Test
    public void printRoomsFacilityRepositoryTypeAll() {
        List<RoomsFacility> guestList = roomsFacilityRepository.findAll();

        System.out.println("RoomsFacilities = " + guestList);
    }

}