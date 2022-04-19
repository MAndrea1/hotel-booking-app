package com.example.hotelbookingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Data
public class RoomAvailabilityDto {

    private Integer roomNumber;
    @NotBlank
    private LocalDate bookingCheckin;
    @NotBlank
    private LocalDate bookingCheckout;
    @NotBlank
    private Integer pax;
    private Integer roomType;
}
