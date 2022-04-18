package com.example.hotelbookingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Data
public class RoomAvailabilityDto {

    @NotBlank
    private Date bookingCheckin;
    @NotBlank
    private Date bookingCheckout;
    @NotBlank
    private Integer pax;
    private Integer roomType;
}
