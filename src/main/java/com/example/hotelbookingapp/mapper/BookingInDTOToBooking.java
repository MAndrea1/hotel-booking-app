package com.example.hotelbookingapp.mapper;

import com.example.hotelbookingapp.dto.BookingDto;
import com.example.hotelbookingapp.model.Booking;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookingInDTOToBooking implements IMapper<BookingDto, Booking> {

    @Override
    public Booking map(BookingDto in) {
        Booking booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setBookingCheckin(in.getBookingCheckin());
        booking.setBookingCheckout(in.getBookingCheckout());
        booking.setBookingBreakfast(in.getBookingBreakfast());
        booking.setBookingStatus("test");
        booking.setFkGuestId(in.getFkGuestId());
        booking.setBookingNotes(in.getBookingNotes());
        return booking;
    }
}
