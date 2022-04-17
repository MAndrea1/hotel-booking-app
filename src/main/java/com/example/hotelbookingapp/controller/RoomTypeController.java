package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.RoomType;
import com.example.hotelbookingapp.service.Imp.RoomTypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomTypeController {

    @Autowired
    private RoomTypeServiceImp roomTypeService;

    @GetMapping({"/roomtypes"})
    public List<RoomType> getRoomTypes() {
        return roomTypeService.findAll();
    }
}
