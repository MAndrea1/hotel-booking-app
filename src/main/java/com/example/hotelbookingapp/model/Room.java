package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
public class Room {
    @Id
    @Column(name = "room_number", nullable = false)
    private Integer id;

    @Column(name = "room_maxpax", nullable = false)
    private Integer roomMaxpax;

    @Column(name = "room_price", nullable = false)
    private BigDecimal roomPrice;

    @Column(name = "room_status")
    private Character roomStatus;

    @ManyToOne
    @JoinColumn(name = "fk_roomtype_id", nullable = false)
    private RoomType fkRoomtype;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rooms_facilities",
            joinColumns = @JoinColumn(name = "fk_room_number"),
            inverseJoinColumns = @JoinColumn(name = "fk_facility_id"))
    private List<Facility> facilities = new ArrayList<>();
}