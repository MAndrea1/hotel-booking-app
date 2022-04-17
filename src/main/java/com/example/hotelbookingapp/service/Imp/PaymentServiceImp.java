package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.model.Payment;
import com.example.hotelbookingapp.repository.PaymentRepository;
import com.example.hotelbookingapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> findById(Integer id) throws Exception {
        return Optional.empty();
    }

    @Override
    public Payment save(Payment entity) throws Exception {
        return null;
    }

    @Override
    public Payment update(Integer id, Payment Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        return false;
    }
}
