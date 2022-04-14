package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.PaymentType;
import com.example.hotelbookingapp.repository.PaymentTypeRepository;
import com.example.hotelbookingapp.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImp implements PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    public List<PaymentType> findAll() {
        return paymentTypeRepository.findAll();
    }
}
