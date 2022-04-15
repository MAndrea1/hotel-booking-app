package com.example.hotelbookingapp.controller;

import com.example.hotelbookingapp.config.TokenGen;
import com.example.hotelbookingapp.dto.JwtDto;
import com.example.hotelbookingapp.dto.LoginUser;
import com.example.hotelbookingapp.dto.SignUpUser;
import com.example.hotelbookingapp.model.Role;
import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.model.UserRole;
import com.example.hotelbookingapp.service.Imp.UserServiceImp;
import com.example.hotelbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenGen tokenGen;

    @PostMapping("/signup")
    public ResponseEntity<?> newUser(@Valid @RequestBody SignUpUser signUpUser, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>("Error in fields", HttpStatus.BAD_REQUEST);

            if (Boolean.TRUE.equals(userServiceImp.existsByEmail(signUpUser.getEmail())))
                return new ResponseEntity<>("Email already in database", HttpStatus.BAD_REQUEST);

            User user = new User(getrole(signUpUser), signUpUser.getEmail(), passwordEncoder.encode(signUpUser.getPassword()));

            return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImp.save(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Couldn't retrieve data from servers, please try again later\"}");
        }
    }

    private UserRole getrole(SignUpUser signUpUser) {
        String role = Role.USER.toString();
        if (signUpUser.getRole().equals(2)) {
            role = Role.ADMIN.toString();
        } else if (signUpUser.getRole().equals(1)) {
            role = Role.SUPERUSER.toString();
        }
        return new UserRole(signUpUser.getRole(), role);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtDto> login (@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userServiceImp.findByUserEmail(loginUser.getEmail()).get().getUserId(), loginUser.getPassword()));

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
