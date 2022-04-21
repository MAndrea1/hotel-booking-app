package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.dto.RoomAvailabilityDto;
import com.example.hotelbookingapp.dto.UpdateRoomDto;
import com.example.hotelbookingapp.model.*;
import com.example.hotelbookingapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping({""})
    public List<Room> getRooms() throws Exception {
        return roomService.findAll();
    }

    @GetMapping("/filter")
    public ResponseEntity<?> findByCustom(@RequestParam String facility) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roomService.findByCustom(facility));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PostMapping("/checkavailability")
    public ResponseEntity<?> checkAvailability(@Valid @RequestBody RoomAvailabilityDto roomAvailabilityDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>("Error in fields", HttpStatus.BAD_REQUEST);
            List<Room> availableRoom = roomService.findAvailable(roomAvailabilityDto);
            return ResponseEntity.status(HttpStatus.OK).body(availableRoom);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PostMapping("/addnewroom")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public ResponseEntity<?> newRoom(@Valid @RequestBody UpdateRoomDto updateRoomDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>("Error in fields", HttpStatus.BAD_REQUEST);
            if (Boolean.TRUE.equals(roomService.existsByRoomNumber(updateRoomDto.getRoomId())))
                return new ResponseEntity<>("Room number already in database", HttpStatus.BAD_REQUEST);
            roomService.save(updateRoomDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Room " + updateRoomDto.getRoomId() + " created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    @PutMapping("/{roomId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public ResponseEntity<?> updateRoom(@PathVariable(value = "roomId") String roomId, @Valid @RequestBody UpdateRoomDto updateRoomDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>("Error in fields", HttpStatus.BAD_REQUEST);
            if (Boolean.FALSE.equals(roomService.existsByRoomNumber(updateRoomDto.getRoomId())))
                return new ResponseEntity<>("Room number not in database", HttpStatus.BAD_REQUEST);
            roomService.update(Integer.valueOf(roomId), updateRoomDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Room " + updateRoomDto.getRoomId() + " updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    @DeleteMapping("/{roomId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public ResponseEntity<?> deleteRoom(@PathVariable(value = "roomId") String roomId){
        try {
            roomService.delete(Integer.valueOf(roomId));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Room deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't delete entity\"}");
        }
    }
}
