package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.dto.BookingDto;
import com.example.hotelbookingapp.service.Imp.BookingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImp bookingService;

    @GetMapping({""})
    public ResponseEntity<?> getAll() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody BookingDto entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't save entity\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByOne(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody BookingDto bookingDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.update(id,bookingDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't update data\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookingService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't delete entity\"}");
        }
    }
}
