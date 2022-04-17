package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.Payment;
import com.example.hotelbookingapp.service.Imp.PaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentServiceImp paymentService;

    @GetMapping({"/payments"})
    public List<Payment> getRooms() {
        return paymentService.findAll();
    }
}
