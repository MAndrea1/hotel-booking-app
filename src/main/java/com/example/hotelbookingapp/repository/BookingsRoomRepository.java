package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.model.BookingsRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRoomRepository extends JpaRepository<BookingsRoom, Integer> {
}