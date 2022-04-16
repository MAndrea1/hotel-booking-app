package com.example.hotelbookingapp.dto;

import lombok.Data;

@Data
public class UpdateUserDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
}
