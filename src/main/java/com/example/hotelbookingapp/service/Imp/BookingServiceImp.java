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

    @Autowired
    private GuestServiceImp guestServiceImp;

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

        if (reserveDto.getBookingCheckin().isAfter(reserveDto.getBookingCheckout())){
            throw new Exception("Check-in date cannot be after check-out date");
        }

        if(LocalDate.now().isAfter(reserveDto.getBookingCheckin())){
            throw new Exception("Check-in date cannot be before current date");
        }

        RoomAvailabilityDto roomAvailabilityDto = new RoomAvailabilityDto();
        roomAvailabilityDto.setRoomNumber(reserveDto.getListRooms().get(0));
        roomAvailabilityDto.setBookingCheckin(reserveDto.getBookingCheckin());
        roomAvailabilityDto.setBookingCheckout(reserveDto.getBookingCheckout());
        roomAvailabilityDto.setPax(1);
        List<Room> availableRooms = roomService.findAvailable(roomAvailabilityDto);

        if (availableRooms.isEmpty()) {
            throw new Exception("--Room not available--");
        }

        if (!guestServiceImp.existsById(reserveDto.getFkGuestId())) {
            throw new Exception("--Guest ID not found--");
        }

        Booking newBooking = new Booking();
        newBooking.setBookingDate(LocalDate.now());
        newBooking.setBookingCheckin(reserveDto.getBookingCheckin());
        newBooking.setBookingCheckout(reserveDto.getBookingCheckout());
        newBooking.setBookingBreakfast(reserveDto.getBookingBreakfast());
        newBooking.setBookingStatus(reserveDto.getStatus());
        newBooking.setFkGuestId(reserveDto.getFkGuestId());
        newBooking.setBookingNotes(reserveDto.getBookingNotes());
        List<Room> rooms = new ArrayList<>();
//        rooms.add(roomService.findById(101).get());
        if (!reserveDto.getListRooms().isEmpty()) {
            for(Integer roomNumber : reserveDto.getListRooms()) {
                rooms.add(roomService.findById(roomNumber).get());
            }
        }
        newBooking.setBookedRooms(rooms);

        if (reserveDto.getPaymentMethod().toString().isEmpty()) {
            throw new Exception("--Please choose a payment method--");
        }

        bookingRepository.save(newBooking);

        Payment payment = new Payment();
        payment.setFkBookingNumber(newBooking);
        payment.setFkPaymenttypeId(paymentTypeServiceImp.findById(reserveDto.getPaymentMethod()).get());
        payment.setPaymentDate(LocalDate.now());

        LocalDate checkin = newBooking.getBookingCheckin();
        LocalDate checkout = newBooking.getBookingCheckout();
        Period period = Period.between(checkin, checkout);
        int days = Math.abs(period.getDays());
        BigDecimal amount = newBooking.getBookedRooms().get(0).getRoomPrice().multiply(BigDecimal.valueOf(days));
        payment.setPaymentsAmount(amount.floatValue());
        paymentService.save(payment);

        return newBooking;
    }
}