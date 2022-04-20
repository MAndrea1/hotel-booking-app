package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.Facility;
import com.example.hotelbookingapp.repository.FacilityRepository;
import com.example.hotelbookingapp.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImp implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    @Override
    public Optional<Facility> findById(Integer id) throws Exception {
        return facilityRepository.findById(id);
    }

    @Override
    public Facility save(Facility entity) throws Exception {
        return null;
    }

    @Override
    public Facility update(Integer id, Facility Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        return false;
    }
}
