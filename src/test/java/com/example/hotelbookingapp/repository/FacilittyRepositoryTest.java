package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Facility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacilittyRepositoryTest {

    @Autowired
    private FacilityRepository facilityRepository;

    @Test
    public void printGuestRepositoryTypeAll() {
        List<Facility> facilityList = facilityRepository.findAll();

        System.out.println("Facilities = " + facilityList);
    }

}