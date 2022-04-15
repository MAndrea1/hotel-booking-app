package com.example.hotelbookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @Column(name = "userrole_id", nullable = false)
    private Integer id;

    @Column(name = "userrole_role", nullable = false)
    private String userroleRole;

}