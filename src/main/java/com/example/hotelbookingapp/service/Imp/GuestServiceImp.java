package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.repository.GuestRepository;
import com.example.hotelbookingapp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GuestServiceImp implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Optional<Guest> findByUserId(Integer userId) {
        return guestRepository.findByUserId(userId);
    }

    @Override
    public Optional<Guest> findById(Integer userId) {
        return guestRepository.findById(userId);
    }

    @Override
    public Boolean existsById(Integer userId) {
        return guestRepository.existsById(userId);
    }

    @Override
    public Guest save(Guest guest) throws Exception {
        try{
            guest = guestRepository.save(guest);
            return guest;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Guest update(Integer id, Guest Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        return false;
    }


}
