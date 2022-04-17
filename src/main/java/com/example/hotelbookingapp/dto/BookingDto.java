package com.example.hotelbookingapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {

    private LocalDate bookingCheckin;

    private LocalDate bookingCheckout;

    private Integer bookingBreakfast;

    private Integer fkGuestId;

    private String bookingNotes;
}
