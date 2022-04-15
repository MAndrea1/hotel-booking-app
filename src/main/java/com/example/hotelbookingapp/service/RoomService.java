package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> findAll();
    Optional<Room> findByRoomNumber(Integer id);
    List<Room> findByCustom(String facility) throws Exception;
}
