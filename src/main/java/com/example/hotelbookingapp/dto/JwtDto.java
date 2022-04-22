package com.example.hotelbookingapp.dto;

import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {

    private String token;
    private UserRole role;
    private String email;
    private String name;

    public JwtDto(String token, User user) {
        this.token = token;
        role = user.getFkUserrole();
        email = user.getUserEmail();
        name = user.getUserEmail();
    }
}
