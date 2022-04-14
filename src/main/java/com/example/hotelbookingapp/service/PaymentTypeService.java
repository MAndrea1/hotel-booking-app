package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.PaymentType;

import java.util.List;

public interface PaymentTypeService {
    List<PaymentType> findAll();
}
