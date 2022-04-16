package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.dto.UpdateUserDto;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User save(User user) throws Exception;
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserId(String userId);
    Boolean existsByEmail (String userEmail);
    List<User> findAllByRole(Integer role);
    List<User> findAllExceptRole(Integer role);
    Boolean delete(Integer id) throws Exception;
    User update(Integer id, UpdateUserDto user) throws Exception;
}
