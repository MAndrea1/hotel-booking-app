package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.dto.UpdateUserDto;
import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.service.Imp.GuestServiceImp;
import com.example.hotelbookingapp.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@PreAuthorize("#userId == authentication.name or hasRole('ADMIN') or hasRole('SUPERADMIN')")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private GuestServiceImp guestService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping({"/allusers"})
    @PreAuthorize("hasRole('SUPERADMIN')")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping({"/users"})
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public List<User> getPlainUsers() {
        return userService.findAllByRole(3);
    }

    @GetMapping("/user/{userId}")
    public Optional<User> getUserById(@PathVariable(value = "userId") String userId, Principal principal) {
        return userService.findByUserId(userId);
    }

    @GetMapping("/user/guest/{userId}")
    public ResponseEntity<?> getGuestById(@PathVariable(value = "userId") String userId, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(guestService.findByUserId(Integer.valueOf(userId)));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable(value = "userId") String userId, @RequestBody UpdateUserDto updateUserDto, Principal principal){
        if (userService.findByUserId(String.valueOf(userId)).get().getFkUserrole().getId() == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, cannot update superadmin\"}");
        }
        try {
            if (!updateUserDto.getPassword().isEmpty()) {
                updateUserDto.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
            }
            userService.update(Integer.valueOf(userId),updateUserDto);
            return ResponseEntity.status(HttpStatus.OK).body("Data for " + userService.findByUserId(userId).get().getUserEmail() + " updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't update data\"}");
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "userId") String userId){
        if (userService.findByUserId(String.valueOf(userId)).get().getFkUserrole().getId() == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, cannot delete superadmin\"}");
        }
        try {
            userService.delete(Integer.valueOf(userId));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, couldn't delete entity\"}");
        }
    }
}
