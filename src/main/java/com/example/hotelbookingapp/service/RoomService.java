package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.dto.RoomAvailabilityDto;
import com.example.hotelbookingapp.dto.UpdateRoomDto;
import com.example.hotelbookingapp.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService extends BaseService<Room, UpdateRoomDto> {

    Optional<Room> findByRoomNumber(Integer id);
    List<Room> findByCustom(String facility) throws Exception;
    Boolean existsByRoomNumber(Integer number);
    List<Room> findAvailable(RoomAvailabilityDto roomAvailabilityDto) throws Exception;
}
