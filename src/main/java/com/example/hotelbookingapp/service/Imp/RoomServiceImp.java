package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.repository.RoomRepository;
import com.example.hotelbookingapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImp implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
