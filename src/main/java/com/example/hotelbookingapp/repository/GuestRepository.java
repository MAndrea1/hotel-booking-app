package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    Optional<Guest> findById(Integer Id);

    @Query(
            value = "SELECT * FROM guests WHERE fk_user_id = :userId",
            nativeQuery = true
    )
    Optional<Guest> findByUserId(@Param("userId") Integer Id);

    boolean existsByFkUserId(Integer id);
    boolean existsByGuestEmail(String email);
}
