package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "guests")
@Data
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id", nullable = false)
    private Integer id;

    @Column(name = "guest_firstname", nullable = false, length = 45)
    private String guestFirstname;

    @Column(name = "guest_lastname", nullable = false, length = 45)
    private String guestLastname;

    @Column(name = "guest_email", nullable = false, length = 45)
    private String guestEmail;

    @Column(name = "guest_phone", length = 45)
    private String guestPhone;

    @Column(name = "guest_country", length = 45)
    private String guestCountry;

    @Column(name = "fk_user_id")
    private Integer fkUserId;
}