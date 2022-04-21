package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.Payment;
import com.example.hotelbookingapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping({"/payments"})
    public List<Payment> getRooms() throws Exception {
        return paymentService.findAll();
    }
}
