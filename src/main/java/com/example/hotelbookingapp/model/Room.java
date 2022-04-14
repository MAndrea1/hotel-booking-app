package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Data
public class Room {
    @Id
    @Column(name = "room_number", nullable = false)
    private Integer id;

    @Column(name = "room_maxpax", nullable = false)
    private Integer roomMaxpax;

    @Column(name = "room_status")
    private Character roomStatus;

    @ManyToOne
    @JoinColumn(name = "fk_roomtype_id", nullable = false)
    private RoomType fkRoomtype;
}