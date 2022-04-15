package com.example.hotelbookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_userrole_id", nullable = false)
    private UserRole fkUserrole;

    @Column(name = "user_email", nullable = false, length = 45)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 100)
    private String userPassword;

    public User(UserRole userRole, String userEmail, String userPassword) {
        fkUserrole = userRole;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

}