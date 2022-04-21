package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.dto.BookingDto;
import com.example.hotelbookingapp.dto.ReserveDto;
import com.example.hotelbookingapp.dto.RoomAvailabilityDto;
import com.example.hotelbookingapp.service.Imp.BookingServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImp bookingService;

    @GetMapping({""})
    @Operation(summary = "GET all bookings", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAll() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    @PostMapping("/reserve")
    @Operation(summary = "POST new booking", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> adminreserve(@RequestBody ReserveDto reserveDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.reserve(reserveDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "GET booking by ID", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getByOne(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "PUT booking by ID", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody BookingDto bookingDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.update(id,bookingDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't update data\"}");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE booking", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookingService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't delete entity\"}");
        }
    }
}
