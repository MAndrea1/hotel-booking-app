package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.BookingsRoom;
import com.example.hotelbookingapp.repository.BookingsRoomRepository;
import com.example.hotelbookingapp.service.BookingsRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingsRoomServiceImp implements BookingsRoomService {

    @Autowired
    private BookingsRoomRepository bookingsRoomRepository;

    @Override
    public List<BookingsRoom> findAll() {
        return bookingsRoomRepository.findAll();
    }
}
