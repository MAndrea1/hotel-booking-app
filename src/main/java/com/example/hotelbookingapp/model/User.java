package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_userrole_id", nullable = false)
    private UserRole fkUserrole;

    @Column(name = "user_email", nullable = false, length = 45)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 100)
    private String userPassword;

}