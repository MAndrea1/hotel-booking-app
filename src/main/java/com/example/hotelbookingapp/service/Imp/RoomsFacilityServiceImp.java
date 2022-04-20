package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.RoomsFacility;
import com.example.hotelbookingapp.repository.RoomsFacilityRepository;
import com.example.hotelbookingapp.service.RoomsFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomsFacilityServiceImp implements RoomsFacilityService {

    @Autowired
    private RoomsFacilityRepository roomsFacilityRepository;

    @Override
    public List<RoomsFacility> findAll() {
        return roomsFacilityRepository.findAll();
    }

    @Override
    public Optional<RoomsFacility> findById(Integer id) throws Exception {
        return Optional.empty();
    }

    @Override
    public RoomsFacility save(RoomsFacility entity) throws Exception {
        return null;
    }

    @Override
    public RoomsFacility update(Integer id, RoomsFacility Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        return false;
    }
}
