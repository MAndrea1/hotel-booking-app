package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facilities")
@Data
public class Facility {
    @Id
    @Column(name = "facility_id", nullable = false)
    private Integer id;

    @Column(name = "facility_detail", nullable = false, length = 45)
    private String facilityDetail;
}