package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.Facility;
import com.example.hotelbookingapp.repository.FacilityRepository;
import com.example.hotelbookingapp.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImp implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }
}
