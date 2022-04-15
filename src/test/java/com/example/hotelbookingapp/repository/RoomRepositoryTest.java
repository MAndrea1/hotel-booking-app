package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.service.Imp.RoomServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomServiceImp roomServiceImp;

    @Test
    public void printRoomTypeAll(){
        List<Room> roomList = roomServiceImp.findAll();
        System.out.println("Room = " + roomList);
    }

    @Test
    public void printRoomByFacility() throws Exception {
        List<Room> roomList = roomServiceImp.findByCustom("Panoramic view");
        System.out.println(roomList);
    }

}