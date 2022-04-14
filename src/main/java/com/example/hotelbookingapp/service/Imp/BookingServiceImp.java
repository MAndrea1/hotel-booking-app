package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.repository.BookingRepository;
import com.example.hotelbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }
}
