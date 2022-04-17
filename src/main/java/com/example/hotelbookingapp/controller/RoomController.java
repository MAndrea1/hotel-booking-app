package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.service.Imp.RoomServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    private RoomServiceImp roomService;

    @GetMapping({"/rooms"})
    public List<Room> getRooms() {
        return roomService.findAll();
    }

    @GetMapping("/rooms/filter")
    public ResponseEntity<?> findByCustom(@RequestParam String facility) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roomService.findByCustom(facility));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

}
