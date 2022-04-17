package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.dto.BookingDto;
import com.example.hotelbookingapp.mapper.BookingInDTOToBooking;
import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.model.User;
import com.example.hotelbookingapp.repository.BookingRepository;
import com.example.hotelbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private final BookingInDTOToBooking bookingInDTOToBooking;

    // Implementation Autowired
    public BookingServiceImp(
            BookingRepository bookingRepository,
            BookingInDTOToBooking bookingInDTOToBooking) {
        this.bookingRepository = bookingRepository;
        this.bookingInDTOToBooking = bookingInDTOToBooking;
    }

    @Override
    public List<Booking> findAll() throws Exception {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> findById(Integer id) throws Exception {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking save(BookingDto entity) throws Exception {
        Booking booking = bookingInDTOToBooking.map(entity);
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking update(Integer id, BookingDto entity) throws Exception {
        try{
            if(entity == null){
                throw new Exception();
            }
            if(!bookingRepository.existsById(id)){
                throw new Exception();
            }
            Booking toUpdate = bookingRepository.findById(id).get();
            toUpdate.setBookingBreakfast(entity.getBookingBreakfast());
            toUpdate.setFkGuestId(entity.getFkGuestId());
            toUpdate.setBookingNotes(entity.getBookingNotes());
            bookingRepository.save(toUpdate);
            return bookingRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        try{
            bookingRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
