package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.BookingsRoom;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.repository.BookingsRoomRepository;
import com.example.hotelbookingapp.service.BookingsRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingsRoomServiceImp implements BookingsRoomService {

    @Autowired
    private BookingsRoomRepository bookingsRoomRepository;

    @Override
    public List<BookingsRoom> findAll() {
        return bookingsRoomRepository.findAll();
    }

    @Override
    public Optional<BookingsRoom> findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public BookingsRoom save(BookingsRoom entity) throws Exception {
        return null;
    }

    @Override
    public BookingsRoom update(Integer id, BookingsRoom Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        return null;
    }
}
