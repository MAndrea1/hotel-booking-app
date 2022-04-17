package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService extends BaseService<Room, Room> {

    Optional<Room> findByRoomNumber(Integer id);
    List<Room> findByCustom(String facility) throws Exception;
}
