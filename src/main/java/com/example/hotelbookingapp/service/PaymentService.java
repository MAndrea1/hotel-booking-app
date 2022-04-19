package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService extends BaseService<Payment, Payment> {
    Optional<Payment> findByFkBookingNumber(Booking booking);
    Boolean delete(Payment payment) throws Exception;
}
