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

    @OneToOne
    @JoinColumn (name = "fk_booking_number", nullable = false)
    private Booking fkBookingNumber;

    @ManyToOne
    @JoinColumn(name = "fk_paymenttype_id", nullable = false)
    private PaymentType fkPaymenttypeId;

    @Column(name = "payments_amount", nullable = false)
    private Float paymentsAmount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
}