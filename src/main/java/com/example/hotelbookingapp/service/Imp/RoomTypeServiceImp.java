package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.model.RoomType;
import com.example.hotelbookingapp.repository.RoomTypeRepository;
import com.example.hotelbookingapp.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImp implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }
}
