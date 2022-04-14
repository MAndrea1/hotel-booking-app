package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.RoomsFacility;
import com.example.hotelbookingapp.repository.RoomsFacilityRepository;
import com.example.hotelbookingapp.service.RoomsFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsFacilityServiceImp implements RoomsFacilityService {

    @Autowired
    private RoomsFacilityRepository roomsFacilityRepository;

    @Override
    public List<RoomsFacility> findAll() {
        return roomsFacilityRepository.findAll();
    }
}
