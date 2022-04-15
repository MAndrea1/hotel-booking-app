package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.repository.RoomRepository;
import com.example.hotelbookingapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImp implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findByRoomNumber(Integer id){
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> findByCustom(String facility) throws Exception {
        try {
            List<Room> rooms = roomRepository.findByCustom(facility);
            return rooms;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    ;
}
