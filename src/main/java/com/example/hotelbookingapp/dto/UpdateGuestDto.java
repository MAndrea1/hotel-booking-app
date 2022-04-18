package com.example.hotelbookingapp.dto;

import lombok.Data;

@Data
public class UpdateGuestDto {

    private String guestEmail;
    private String guestFirstname;
    private String guestLastname;
    private String guestPhone;
    private String guestCountry;
}
