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
public class RoomsFacilityId implements Serializable {
    private static final long serialVersionUID = -735072334848189307L;
    @Column(name = "fk_room_number", nullable = false)
    private Integer fkRoomNumber;
    @Column(name = "fk_roomfacility_id", nullable = false)
    private Integer fkRoomfacilityId;

    public Integer getFkRoomfacilityId() {
        return fkRoomfacilityId;
    }

    public void setFkRoomfacilityId(Integer fkRoomfacilityId) {
        this.fkRoomfacilityId = fkRoomfacilityId;
    }

    public Integer getFkRoomNumber() {
        return fkRoomNumber;
    }

    public void setFkRoomNumber(Integer fkRoomNumber) {
        this.fkRoomNumber = fkRoomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkRoomNumber, fkRoomfacilityId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoomsFacilityId entity = (RoomsFacilityId) o;
        return Objects.equals(this.fkRoomNumber, entity.fkRoomNumber) &&
                Objects.equals(this.fkRoomfacilityId, entity.fkRoomfacilityId);
    }
}