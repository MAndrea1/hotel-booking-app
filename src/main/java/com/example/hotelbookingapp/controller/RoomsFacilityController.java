package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.BookingsRoom;
import com.example.hotelbookingapp.model.RoomsFacility;
import com.example.hotelbookingapp.service.BookingsRoomService;
import com.example.hotelbookingapp.service.RoomsFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomsFacilityController {

    @Autowired
    private RoomsFacilityService roomsFacilityService;

    @GetMapping({"/roomfacilities"})
    public List<RoomsFacility> getFacilities() {
        return roomsFacilityService.findAll();
    }

    @GetMapping({"/roomfacilities/{id}"})
    public List<RoomsFacility> getFacilityListForId() {
        return roomsFacilityService.findAll();
    }

}
