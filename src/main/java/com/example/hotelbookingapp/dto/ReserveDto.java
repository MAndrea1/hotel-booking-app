package com.example.hotelbookingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
public class ReserveDto {

    @NotBlank
    private List<Integer> listRooms;
    @NotBlank
    private LocalDate bookingCheckin;
    @NotBlank
    private LocalDate bookingCheckout;
    @NotBlank
    private Integer bookingBreakfast;
    @NotBlank
    private Integer fkGuestId;
    @NotBlank
    private String bookingNotes;
    @NotBlank
    private String status;
    @NotBlank
    private Integer paymentMethod;
}
