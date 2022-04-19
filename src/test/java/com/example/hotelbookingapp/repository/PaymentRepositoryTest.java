package com.example.hotelbookingapp.repository;

import com.example.hotelbookingapp.HotelBookingAppApplication;
import com.example.hotelbookingapp.model.Booking;
import com.example.hotelbookingapp.model.Payment;
import com.example.hotelbookingapp.model.Room;
import com.example.hotelbookingapp.service.Imp.BookingServiceImp;
import com.example.hotelbookingapp.service.Imp.PaymentServiceImp;
import com.example.hotelbookingapp.service.Imp.PaymentTypeServiceImp;
import com.example.hotelbookingapp.service.Imp.RoomServiceImp;
import org.joda.time.DateTime;
import org.joda.time.Days;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest(classes = HotelBookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingServiceImp bookingService;

    @Autowired
    private RoomServiceImp roomService;

    @Autowired
    private PaymentServiceImp paymentService;

    @Autowired
    private PaymentTypeServiceImp paymentTypeServiceImp;

    @Test
    public void printPaymentRepositoryTypeAll() {
        List<Payment> paymentList = paymentRepository.findAll();

        System.out.println("Payments = " + paymentList);
    }

    @Test
    public void printSavePayment() throws Exception {
        Payment payment = new Payment();
        payment.setFkBookingNumber(bookingService.findById(1001).get());
        payment.setFkPaymenttypeId(paymentTypeServiceImp.findById(1).get());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentsAmount(5F);
        paymentRepository.save(payment);

        System.out.println("Payments = " + paymentRepository.findById(9));
    }

    @Test
    public void printFindByPayment() throws Exception {
        System.out.println("payment: " + paymentRepository.findByFkBookingNumber(bookingService.findById(1007).get()));
    }

    @Test
    public void printSavePaymentWithDays() throws Exception {
        Booking booking = bookingService.findById(1001).get();

        Payment payment = new Payment();
        payment.setFkBookingNumber(booking);
        payment.setFkPaymenttypeId(paymentTypeServiceImp.findById(1).get());
        payment.setPaymentDate(LocalDate.now());

        LocalDate checkin = booking.getBookingCheckin();
        LocalDate checkout = booking.getBookingCheckout();
        Period period = Period.between(checkin, checkout);
        int days = Math.abs(period.getDays());
        BigDecimal amount = booking.getBookedRooms().get(0).getRoomPrice().multiply(BigDecimal.valueOf(days));
        payment.setPaymentsAmount(amount.floatValue());

        paymentRepository.save(payment);
    }

}