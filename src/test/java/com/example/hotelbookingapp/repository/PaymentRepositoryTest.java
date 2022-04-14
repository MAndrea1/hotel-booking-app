package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void printGuestRepositoryTypeAll() {
        List<Payment> paymentList = paymentRepository.findAll();

        System.out.println("Payments = " + paymentList);
    }

}