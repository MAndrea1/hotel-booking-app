package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.dto.BookingDto;
import com.example.hotelbookingapp.dto.ReserveDto;
import com.example.hotelbookingapp.dto.RoomAvailabilityDto;
import com.example.hotelbookingapp.mapper.BookingInDTOToBooking;
import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.model.Payment;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.repository.BookingRepository;
import com.example.hotelbookingapp.service.BookingService;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomServiceImp roomService;

    @Autowired
    private PaymentServiceImp paymentService;

    @Autowired
    private PaymentTypeServiceImp paymentTypeServiceImp;

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
            Payment payment = paymentService.findByFkBookingNumber(bookingRepository.findById(id).get()).get();
            paymentService.delete(payment);
            bookingRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Booking reserve(ReserveDto reserveDto) throws Exception {

        RoomAvailabilityDto roomAvailabilityDto = new RoomAvailabilityDto();
        roomAvailabilityDto.setRoomNumber(reserveDto.getListRooms().get(0));
        roomAvailabilityDto.setBookingCheckin(reserveDto.getBookingCheckin());
        roomAvailabilityDto.setBookingCheckout(reserveDto.getBookingCheckout());
        roomAvailabilityDto.setPax(1);
        List<Room> availableRooms = roomService.findAvailable(roomAvailabilityDto);

        if (availableRooms.isEmpty()) {
            throw new Exception("--Room not available--");
        }

        Booking booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setBookingCheckin(reserveDto.getBookingCheckin());
        booking.setBookingCheckout(reserveDto.getBookingCheckout());
        booking.setBookingBreakfast(reserveDto.getBookingBreakfast());
        booking.setBookingStatus(reserveDto.getStatus());
        booking.setFkGuestId(reserveDto.getFkGuestId());
        booking.setBookingNotes(reserveDto.getBookingNotes());
        List<Room> rooms = new ArrayList<>();
        if (!reserveDto.getListRooms().isEmpty()) {
            for(Integer roomNumber : reserveDto.getListRooms()) {
                rooms.add(roomService.findById(roomNumber).get());
            }
        }
        booking.setBookedRooms(rooms);
        bookingRepository.save(booking);

        Payment payment = new Payment();
        payment.setFkBookingNumber(booking);
        payment.setFkPaymenttypeId(paymentTypeServiceImp.findById(reserveDto.getPaymentMethod()).get());
        payment.setPaymentDate(LocalDate.now());

        LocalDate checkin = booking.getBookingCheckin();
        LocalDate checkout = booking.getBookingCheckout();
        Period period = Period.between(checkin, checkout);
        int days = Math.abs(period.getDays());
        BigDecimal amount = booking.getBookedRooms().get(0).getRoomPrice().multiply(BigDecimal.valueOf(days));
        payment.setPaymentsAmount(amount.floatValue());

        paymentService.save(payment);

        return booking;
    }
}