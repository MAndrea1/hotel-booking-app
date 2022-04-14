package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.model.Payment;
import com.example.hotelbookingapp.repository.PaymentRepository;
import com.example.hotelbookingapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}
