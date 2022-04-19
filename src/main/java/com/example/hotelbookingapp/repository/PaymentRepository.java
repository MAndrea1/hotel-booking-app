package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByFkBookingNumber(Booking booking);
}
