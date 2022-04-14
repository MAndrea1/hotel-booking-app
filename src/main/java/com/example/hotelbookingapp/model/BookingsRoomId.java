package com.example.hotelbookingapp.model;

import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@ToString
public class BookingsRoomId implements Serializable {
    private static final long serialVersionUID = 105662212788996463L;
    @Column(name = "fk_booking_number", nullable = false)
    private Integer fkBookingNumber;
    @Column(name = "fk_room_number", nullable = false)
    private Integer fkRoomNumber;

    public Integer getFkRoomNumber() {
        return fkRoomNumber;
    }

    public void setFkRoomNumber(Integer fkRoomNumber) {
        this.fkRoomNumber = fkRoomNumber;
    }

    public Integer getFkBookingNumber() {
        return fkBookingNumber;
    }

    public void setFkBookingNumber(Integer fkBookingNumber) {
        this.fkBookingNumber = fkBookingNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkBookingNumber, fkRoomNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookingsRoomId entity = (BookingsRoomId) o;
        return Objects.equals(this.fkBookingNumber, entity.fkBookingNumber) &&
                Objects.equals(this.fkRoomNumber, entity.fkRoomNumber);
    }
}