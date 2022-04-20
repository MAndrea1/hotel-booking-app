package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.dto.SignUpUser;
import com.example.hotelbookingapp.dto.UpdateUserDto;
import com.example.hotelbookingapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User, UpdateUserDto> {

    User save(User user) throws Exception;
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserId(String userId);
    Boolean existsByEmail (String userEmail);
    List<User> findAllByRole(Integer role);
    List<User> findAllExceptRole(Integer role);
    User save(SignUpUser signUpUser) throws Exception;

}
