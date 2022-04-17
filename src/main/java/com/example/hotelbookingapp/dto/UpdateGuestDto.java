package com.example.hotelbookingapp.dto;

import lombok.Data;

@Data
public class UpdateGuestDto {

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
}
