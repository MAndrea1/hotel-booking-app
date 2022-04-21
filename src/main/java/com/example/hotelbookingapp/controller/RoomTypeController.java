package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.RoomType;
import com.example.hotelbookingapp.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping({"/roomtypes"})
    public List<RoomType> getRoomTypes() throws Exception {
        return roomTypeService.findAll();
    }
}
