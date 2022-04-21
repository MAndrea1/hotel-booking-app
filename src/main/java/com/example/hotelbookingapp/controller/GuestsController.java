package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.dto.UpdateGuestDto;
import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.service.Imp.GuestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/guests")
public class GuestsController {

    @Autowired
    private GuestServiceImp guestService;

    @GetMapping({""})
    public List<Guest> getGuests() {
        return guestService.findAll();
    }

    @GetMapping("/{guestId}")
    public ResponseEntity<?> getGuestById(@PathVariable(value = "guestId") String guestId, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(guestService.findById(Integer.valueOf(guestId)));
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<?> updateGuestById(@PathVariable(value = "guestId") String guestId, @RequestBody UpdateGuestDto updateGuestDto, Principal principal) {
        try{
            guestService.update(Integer.valueOf(guestId), updateGuestDto);
            return ResponseEntity.status(HttpStatus.OK).body("Data for " + guestService.findById(Integer.valueOf(guestId)).get().getGuestEmail() + " updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't update data\"}");
        }
    }

    @PostMapping("/addnewguest")
    public ResponseEntity<?> newUser(@Valid @RequestBody UpdateGuestDto updateGuestDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>("Error in fields", HttpStatus.BAD_REQUEST);
            if (Boolean.TRUE.equals(guestService.existByEmail(updateGuestDto.getGuestEmail())))
                return new ResponseEntity<>("Email already in database", HttpStatus.BAD_REQUEST);

            Guest newGuest = new Guest();
            newGuest.setGuestFirstname(updateGuestDto.getGuestFirstname());
            newGuest.setGuestLastname(updateGuestDto.getGuestLastname());
            newGuest.setGuestEmail(updateGuestDto.getGuestEmail());
            newGuest.setGuestPhone(updateGuestDto.getGuestPhone());
            newGuest.setGuestCountry(updateGuestDto.getGuestCountry());
            guestService.save(newGuest);

            return ResponseEntity.status(HttpStatus.CREATED).body("User " + updateGuestDto.getGuestEmail() + " created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<?> deleteGuestById(@PathVariable(value = "guestId") String guestId){
        try {
            guestService.delete(Integer.valueOf(guestId));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Guest deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't delete entity\"}");
        }
    }

}
