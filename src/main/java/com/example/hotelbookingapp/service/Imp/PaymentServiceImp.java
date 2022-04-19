package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.model.Payment;
import com.example.hotelbookingapp.repository.PaymentRepository;
import com.example.hotelbookingapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> findById(Integer id) throws Exception {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment save(Payment entity) throws Exception {
        return paymentRepository.save(entity);
    }

    @Override
    public Payment update(Integer id, Payment Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        try{
            paymentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Payment payment) throws Exception {
        try{
            paymentRepository.delete(payment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Payment> findByFkBookingNumber(Booking booking) {
        return paymentRepository.findByFkBookingNumber(booking);
    }
}
