package com.example.hotelbookingapp.model;

import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bookings_rooms")
@ToString
public class BookingsRoom {
    @EmbeddedId
    private BookingsRoomId id;

    public BookingsRoomId getId() {
        return id;
    }

    public void setId(BookingsRoomId id) {
        this.id = id;
    }
}