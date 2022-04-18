package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.dto.RoomAvailabilityDto;
import com.example.hotelbookingapp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(
            value = "SELECT * FROM rooms JOIN rooms_facilities ON fk_room_number = room_number JOIN facilities ON facility_id = fk_facility_id WHERE facility_detail = :facility ",
            nativeQuery = true
    )
    List<Room> findByCustom(@Param("facility") String facility);

    @Query(
            value = "SELECT * FROM rooms JOIN bookings_rooms ON fk_room_number = room_number JOIN bookings ON fk_booking_id = booking_id WHERE booking_checkin NOT BETWEEN :startDate AND :endDate AND booking_checkout NOT BETWEEN :startDate AND :endDate AND room_maxpax >= :maxpax AND fk_roomtype_id = :roomtype",
            nativeQuery = true
    )
    List<Room> findAvailable(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("maxpax") Integer maxpax,
            @Param("roomtype") Integer roomtype
    );
}
