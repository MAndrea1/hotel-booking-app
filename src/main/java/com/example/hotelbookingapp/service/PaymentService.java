package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();
}
