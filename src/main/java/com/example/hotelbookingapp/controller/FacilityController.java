package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.Facility;
import com.example.hotelbookingapp.service.FacilityService;
import com.example.hotelbookingapp.service.Imp.FacilityServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api")
public class FacilityController {

    @Autowired
    private FacilityServiceImp facilityService;

    @GetMapping({"/facilities"})
    public List<Facility> getFacilities() {
       // return facilityRepository.findAll();
        return facilityService.findAll();
    }
}
