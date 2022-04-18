package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Facility;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.service.Imp.RoomServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomServiceImp roomServiceImp;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private FacilityRepository FacilityRepository;

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

    @Test
    public void printRoomIfExists() throws Exception{
        System.out.println(roomServiceImp.existsByRoomNumber(999));
    }

    @Test
    public void printAddNewRoom() throws Exception{

        Room newRoom = new Room();
        newRoom.setId(888);
        newRoom.setRoomMaxpax(8);
        newRoom.setRoomPrice(BigDecimal.valueOf(8888));
        newRoom.setRoomStatus(null);
        newRoom.setFkRoomtype(roomTypeRepository.findById(1).get());
        List<Facility> facilities = new ArrayList<>();
        facilities.add(FacilityRepository.findById(1).get());
        facilities.add(FacilityRepository.findById(2).get());
        newRoom.setFacilities(facilities);
        roomRepository.save(newRoom);
        System.out.println(roomRepository.findById(888));
    }

    @Test
    public void printFindAvailable() throws Exception{
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-16");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-20");
        Integer maxpax = 4;
        Integer roomType = 3;
        System.out.println(roomRepository.findAvailable(startDate,endDate,maxpax,roomType));
    }

}