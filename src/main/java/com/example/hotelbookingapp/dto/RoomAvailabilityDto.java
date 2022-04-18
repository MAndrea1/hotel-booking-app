package com.example.hotelbookingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class RoomAvailabilityDto {

    private Integer roomNumber;
    @NotBlank
    private Date bookingCheckin;
    @NotBlank
    private Date bookingCheckout;
    @NotBlank
    private Integer pax;
    private Integer roomType;
}
