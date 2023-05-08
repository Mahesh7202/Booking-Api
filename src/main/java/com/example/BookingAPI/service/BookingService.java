package com.example.BookingAPI.service;

import com.example.BookingAPI.model.Booking;
import com.example.BookingAPI.model.BookingApi;
import com.example.BookingAPI.model.Errors;
import com.example.BookingAPI.model.availability.SearchAvailbilityQuery;
import com.example.BookingAPI.model.dto.AvailabilityDto;
import com.example.BookingAPI.model.dto.BookingDto;
import com.example.BookingAPI.model.dto.BookingListDto;
import com.example.BookingAPI.model.dto.CancelBookingDto;
import com.example.BookingAPI.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.BookingAPI.model.Enum.Category.INVALID_REQUEST_ERROR;
import static com.example.BookingAPI.model.Enum.Code.MISSING_REQUIRED_PARAMETER;
import static com.example.BookingAPI.model.Enum.Status.CANCELLED_BY_CUSTOMER;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;


    public BookingApi createBooking(BookingDto bookingDto){
        log.info("Inside [service], request to create a booking{}",bookingDto.getBooking());
        BookingApi bookingApi = new BookingApi();
        List<Errors> errors = new ArrayList<>();
        if (bookingDto.getBooking() == null){
                log.error("No booking data was Found");
                errors.add(new Errors(INVALID_REQUEST_ERROR, MISSING_REQUIRED_PARAMETER, "Booking data Not Found", "booking"));
                bookingApi.setErrors(errors);
                return bookingApi;
        }else if(getBookingById(bookingDto.getBooking().getId())!=null){
            log.error("The Booking with id was already exist");
            errors.add(new Errors());
            bookingApi.setErrors(errors);
            return bookingApi;
        }
        Booking booking = bookingRepository.save(bookingDto.getBooking());

        if(booking == null){
            log.error("Error at the creating the booking");
            errors.add(new Errors());
            bookingApi.setErrors(errors);
            return bookingApi;
        }
        log.info("The booking data was successfully created");
        bookingApi.setBooking(booking);
        return bookingApi;
    }


    public BookingApi updateBooking(String booking_id, BookingDto bookingDto) {
        BookingApi bookingApi = new BookingApi();
        List<Errors> errors = new ArrayList<>();
       /* if (bookingDto.getBooking() == null) {
            errors.add(new Errors(INVALID_REQUEST_ERROR,MISSING_REQUIRED_PARAMETER,"Booking data Not Found","booking"));
            bookingApi.setErrors(errors);
            return bookingApi;
        }*/
        Booking booking = getBookingById(booking_id);
        /*if(booking == null){
            errors.add(new Errors());
            bookingApi.setErrors(errors);
            return bookingApi;
        }*/
        bookingApi.setBooking(booking);
        //created a temporary bookingApi object with existing booking of booking id

        //updating the Booking
        bookingApi.setBooking(bookingApi.getBooking());
        bookingApi.setIdempotency_key(bookingApi.getIdempotency_key());

        bookingRepository.save(bookingDto.getBooking());
        return bookingApi;
    }

    public Booking getBookingById(String booking_id){
        Optional<Booking> booking = bookingRepository.findById(booking_id);
        return booking.get();
    }



    public BookingListDto listBookings(Integer limit,
                                       String cursor,
                                       String team_member_id,
                                       String location_id) {

        List<Booking> bookings = bookingRepository.findAll();
       if(location_id!=null){
               List<Booking> tmp = new ArrayList<>();
               bookings.stream().forEach(
                       booking -> {
                           if(booking.getLocation_id().equals(location_id)) {
                               tmp.add(booking);
                           }
                       }
               );
               tmp.stream().forEach(bookings::add);
       }else if(team_member_id!=null){
           List<Booking> tmp = new ArrayList<>();
           bookings.stream().forEach(
                   booking -> {
                       booking.getAppointment_segments().stream().forEach(
                               appointmentSegments -> {
                                   if(appointmentSegments.getTeam_member_id().equals(team_member_id)) {
                                   tmp.add(booking);
                               }
                       });
                   });
           tmp.stream().forEach(bookings::add);
       }

       return new BookingListDto(bookings,cursor);
    }

    public BookingApi cancelBooking(String booking_id, CancelBookingDto cancelBookingDto){
        BookingApi bookingApi = new BookingApi();
        List<Errors> errors = new ArrayList<>();
        Booking booking = getBookingById(booking_id);
        if(booking == null){
            errors.add(new Errors());
            bookingApi.setErrors(errors);
            return bookingApi;
        }
        bookingApi.setBooking(booking);

        bookingApi.getBooking().setStatus(CANCELLED_BY_CUSTOMER);
        bookingApi.getBooking().setVersion(cancelBookingDto.getBooking_version());

        bookingRepository.save(bookingApi.getBooking());

        return bookingApi;
    }


    public AvailabilityDto searchAvailability(SearchAvailbilityQuery query) {

        return null;

    }
}
