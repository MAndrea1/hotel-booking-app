package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roomtypes")
@Data
public class RoomType {
    @Id
    @Column(name = "roomtype_id", nullable = false)
    private Integer id;

    @Column(name = "roomtype_detail", nullable = false)
    private String roomtypeDetail;

}