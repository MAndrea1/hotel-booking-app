package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(
            value = "SELECT * FROM rooms JOIN rooms_facilities ON fk_room_number = room_number JOIN facilities ON facility_id = fk_facility_id WHERE facility_detail = :facility ",
            nativeQuery = true
    )
    List<Room> findByCustom(@Param("facility") String facility);


}
