package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.PaymentType;
import com.example.hotelbookingapp.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api")
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping({"/paymenttypes"})
    public List<PaymentType> getPaymentTypes() throws Exception {
        return paymentTypeService.findAll();
    }
}
