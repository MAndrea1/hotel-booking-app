package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserId(Integer Id);

    Boolean existsByUserEmail (String userEmail);

    @Query(
            value = "SELECT * FROM mtbvbgfw_bd_hotel.users WHERE fk_userrole_id = :role ",
            nativeQuery = true
    )
    List<User> findAllByRole(@Param("role") Integer role);

    @Query(
            value = "SELECT * FROM mtbvbgfw_bd_hotel.users WHERE fk_userrole_id <> :role ",
            nativeQuery = true
    )
    List<User> findAllExceptRole(@Param("role") Integer role);
}
