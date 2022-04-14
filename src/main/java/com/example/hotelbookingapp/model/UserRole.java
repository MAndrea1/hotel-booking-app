package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userroles")
@Data
public class UserRole {
    @Id
    @Column(name = "userrole_id", nullable = false)
    private Integer id;

    @Column(name = "userrole_role", nullable = false)
    private String userroleRole;

}