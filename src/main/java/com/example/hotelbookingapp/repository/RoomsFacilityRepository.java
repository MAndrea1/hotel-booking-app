package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.model.RoomsFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsFacilityRepository extends JpaRepository<RoomsFacility, Integer> {

}