package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.config.TokenGen;
import com.example.hotelbookingapp.dto.JwtDto;
import com.example.hotelbookingapp.dto.LoginUser;
import com.example.hotelbookingapp.dto.SignUpUser;
import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.service.GuestService;
import com.example.hotelbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    GuestService guestService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenGen tokenGen;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> newUser(@Valid @RequestBody SignUpUser signUpUser, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>("Error in fields", HttpStatus.BAD_REQUEST);
            if (Boolean.TRUE.equals(userService.existsByEmail(signUpUser.getEmail())))
                return new ResponseEntity<>("Email already in database", HttpStatus.BAD_REQUEST);

            signUpUser.setPassword(passwordEncoder.encode(signUpUser.getPassword()));
            signUpUser.setRole(3);

            User newUser = userService.save(signUpUser);

            Guest newGuest = new Guest();
            newGuest.setGuestFirstname(signUpUser.getFirstName());
            newGuest.setGuestLastname(signUpUser.getLastName());
            newGuest.setGuestEmail(signUpUser.getEmail());
            newGuest.setGuestPhone(signUpUser.getPhone());
            newGuest.setGuestCountry(signUpUser.getCountry());
            newGuest.setFkUserId(newUser.getUserId());
            guestService.save(newGuest);

            return ResponseEntity.status(HttpStatus.CREATED).body("User " + signUpUser.getEmail() + " created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    @PostMapping("/adminsignup")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public ResponseEntity<?> newAdmin(@Valid @RequestBody SignUpUser signUpUser, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>("Error in fields", HttpStatus.BAD_REQUEST);
            if (Boolean.TRUE.equals(userService.existsByEmail(signUpUser.getEmail())))
                return new ResponseEntity<>("Email already in database", HttpStatus.BAD_REQUEST);

            signUpUser.setPassword(passwordEncoder.encode(signUpUser.getPassword()));
            signUpUser.setRole(2);
            User newUser = userService.save(signUpUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin " + signUpUser.getEmail() + " created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login (@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity("Field missing", HttpStatus.BAD_REQUEST);

            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            userService.findByUserEmail(loginUser.getEmail()).get().getUserId(), loginUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenGen.generateToken(authentication);
            JwtDto jwtDto = new JwtDto(jwt);
            return new ResponseEntity<>(jwtDto,HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtDto> refreshToken (@RequestBody JwtDto jwtDto) throws ParseException {
        String token = tokenGen.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}
