package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void printRoomTypeAll(){
        List<Room> roomList = roomRepository.findAll();

        System.out.println("Room = " + roomList);
    }

}