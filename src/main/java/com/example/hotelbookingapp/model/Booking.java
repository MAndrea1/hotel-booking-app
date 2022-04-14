package com.example.hotelbookingapp.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Integer id;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "booking_checkin", nullable = false)
    private LocalDate bookingCheckin;

    @Column(name = "booking_checkout", nullable = false)
    private LocalDate bookingCheckout;

    @Column(name = "booking_breakfast", nullable = false)
    private Integer bookingBreakfast;

    @Column(name = "booking_status", length = 45)
    private String bookingStatus;

    @Column(name = "fk_guest_id", nullable = false)
    private Integer fkGuestId;

    @Column(name = "booking_notes")
    private String bookingNotes;
}