package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void printGuestRepositoryTypeAll() {
        List<User> userList = userRepository.findAll();

        System.out.println("Users = " + userList);
    }

}