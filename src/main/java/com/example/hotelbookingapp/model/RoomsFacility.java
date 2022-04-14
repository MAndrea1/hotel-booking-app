package com.example.hotelbookingapp.model;

import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rooms_facilities")
@ToString
public class RoomsFacility {
    @EmbeddedId
    private RoomsFacilityId id;

    public RoomsFacilityId getId() {
        return id;
    }

    public void setId(RoomsFacilityId id) {
        this.id = id;
    }
}