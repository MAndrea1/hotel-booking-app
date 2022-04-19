package com.example.hotelbookingapp.service.Imp;


import com.example.hotelbookingapp.dto.RoomAvailabilityDto;
import com.example.hotelbookingapp.dto.UpdateRoomDto;
import com.example.hotelbookingapp.model.Facility;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.repository.RoomRepository;
import com.example.hotelbookingapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImp implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeServiceImp roomTypeService;

    @Autowired
    private FacilityServiceImp facilityService;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Integer id) throws Exception {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(UpdateRoomDto updateRoomDto) throws Exception {
        Room newRoom = new Room();
        newRoom.setId(updateRoomDto.getRoomId());
        newRoom.setRoomMaxpax(updateRoomDto.getRoomMaxpax());
        newRoom.setRoomPrice(updateRoomDto.getRoomPrice());
        newRoom.setRoomStatus(updateRoomDto.getRoomStatus());
        newRoom.setFkRoomtype(roomTypeService.findById(updateRoomDto.getRoomType()).get());
        List<Facility> facilities = new ArrayList<>();
        if (!updateRoomDto.getListFacilities().isEmpty()) {
            for(Integer facilityid : updateRoomDto.getListFacilities()) {
                facilities.add(facilityService.findById(facilityid).get());
            }
        }
        newRoom.setFacilities(facilities);
        roomRepository.save(newRoom);
        return roomRepository.findById(updateRoomDto.getRoomId()).get();
    }

    @Override
    public Room update(Integer id, UpdateRoomDto updateRoomDto) throws Exception {
        Room updateRoom = roomRepository.findById(id).get();
        if (updateRoomDto.getRoomId() != 0) {
            updateRoom.setId(updateRoomDto.getRoomId());
        }
        if (updateRoomDto.getRoomMaxpax() > 0) {
            updateRoom.setRoomMaxpax(updateRoomDto.getRoomMaxpax());
        }
        if (updateRoomDto.getRoomPrice().compareTo(BigDecimal.ZERO) > 0) {
            updateRoom.setRoomPrice(updateRoomDto.getRoomPrice());
        }
        updateRoom.setRoomStatus(updateRoomDto.getRoomStatus());
        updateRoom.setFkRoomtype(roomTypeService.findById(updateRoomDto.getRoomType()).get());
        List<Facility> facilities = new ArrayList<>();
        if (!updateRoomDto.getListFacilities().isEmpty()) {
            for(Integer facilityid : updateRoomDto.getListFacilities()) {
                facilities.add(facilityService.findById(facilityid).get());
            }
        }
        updateRoom.setFacilities(facilities);
        roomRepository.save(updateRoom);
        return roomRepository.findById(updateRoomDto.getRoomId()).get();
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        try{
            if(roomRepository.existsById(id)){
                roomRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Room> findByRoomNumber(Integer id){
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> findByCustom(String facility) throws Exception {
        try {
            List<Room> rooms = roomRepository.findByCustom(facility);
            return rooms;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean existsByRoomNumber(Integer number) {
        return roomRepository.existsById(number);
    }

    @Override
    public List<Room> findAvailable(RoomAvailabilityDto roomAvailabilityDto) throws Exception {
        try {
            List<Room> rooms = roomRepository.findAvailable(roomAvailabilityDto.getRoomNumber(), roomAvailabilityDto.getBookingCheckin(), roomAvailabilityDto.getBookingCheckout(), roomAvailabilityDto.getPax(), roomAvailabilityDto.getRoomType());
            return rooms;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
