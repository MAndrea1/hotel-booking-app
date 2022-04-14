package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.PaymentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentTypeRepositoryTest {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Test
    public void printGuestRepositoryTypeAll() {
        List<PaymentType> paymentTypeList = paymentTypeRepository.findAll();

        System.out.println("Payment Types = " + paymentTypeList);
    }

}