package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.config.AuthenticationEP;
import com.example.hotelbookingapp.dto.UpdateGuestDto;
import com.example.hotelbookingapp.model.Guest;
import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.repository.GuestRepository;
import com.example.hotelbookingapp.service.GuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GuestServiceImp implements GuestService {

    private final static Logger logger = LoggerFactory.getLogger(GuestService.class);

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
    public Guest save(UpdateGuestDto entity) throws Exception {
        return null;
    }

    @Override
    public Guest update(Integer id, UpdateGuestDto updateGuestDto) throws Exception {
        logger.debug("-- Guest update --");
        try{
            if(updateGuestDto == null){
                throw new Exception();
            }
            if(!guestRepository.existsById(id)){
                throw new Exception();
            }
            logger.debug("-- No null - Guest exists --");
            Guest updatedGuest = guestRepository.findById(id).get();
            buildGuest(updatedGuest, updateGuestDto);
            return guestRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean existsById(Integer userId) {
        return guestRepository.existsById(userId);
    }

    @Override
    public Boolean existByEmail(String email) {
        return guestRepository.existsByGuestEmail(email);
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
    public Guest updateByUserId(Integer userId, UpdateGuestDto updateGuestDto) throws Exception {
        try{
            if(updateGuestDto == null){
                throw new Exception();
            }
            if(!guestRepository.existsByFkUserId(userId)){
                throw new Exception();
            }
            Guest updatedGuest = guestRepository.findByUserId(userId).get();
            buildGuest(updatedGuest, updateGuestDto);
            return guestRepository.findByUserId(userId).get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private void buildGuest(Guest updatedGuest, UpdateGuestDto updateGuestDto) {
        if (!updateGuestDto.getGuestEmail().isEmpty()) {
            updatedGuest.setGuestEmail(updateGuestDto.getGuestEmail());
        }
        if (!updateGuestDto.getGuestFirstname().isEmpty()) {
            updatedGuest.setGuestFirstname(updateGuestDto.getGuestFirstname());
        }
        if (!updateGuestDto.getGuestLastname().isEmpty()) {
            updatedGuest.setGuestLastname(updateGuestDto.getGuestLastname());
        }
        if (!updateGuestDto.getGuestPhone().isEmpty()) {
            updatedGuest.setGuestPhone(updateGuestDto.getGuestPhone());
        }
        if (!updateGuestDto.getGuestCountry().isEmpty()) {
            updatedGuest.setGuestCountry(updateGuestDto.getGuestCountry());
        }
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        try{
            if(guestRepository.existsById(id)){
                guestRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
