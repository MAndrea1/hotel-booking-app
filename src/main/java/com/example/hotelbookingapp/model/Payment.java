package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @Column(name = "fk_booking_number", nullable = false)
    private Integer fkBookingNumber;

    @Column(name = "fk_paymenttype_id", nullable = false)
    private Integer fkPaymenttypeId;

    @Column(name = "payments_amount", nullable = false)
    private Double paymentsAmount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
}