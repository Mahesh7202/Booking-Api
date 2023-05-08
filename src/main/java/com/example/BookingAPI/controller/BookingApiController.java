package com.example.BookingAPI.controller;


import com.example.BookingAPI.model.availability.SearchAvailbilityQuery;
import com.example.BookingAPI.model.dto.*;
import com.example.BookingAPI.model.BookingApi;
import com.example.BookingAPI.service.BookingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.BookingAPI.model.Enum.ErrorConstants.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@Slf4j
@RequestMapping("v2/bookings")
public class BookingApiController {

    @Autowired
    private BookingService bookingService;



    @GetMapping
    public ResponseEntity<?> listBookings(
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "cursor",required = false) String cursor,
            @RequestParam(value = "team_member_id",required = false) String team_member_id,
            @RequestParam(value = "location_id",required = false) String location_id){

        log.info("Inside [/v2/bookings], request to retrieve list of bookings");
        BookingListDto bookingListDto = bookingService.listBookings(limit,cursor,team_member_id,location_id);
        log.info("Bookings are retrieved successfully");
        return ResponseEntity.ok().body(bookingListDto);
    }



    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDto bookingDto){
        log.info("Inside [/v2/bookings], request to create a booking:{}",bookingDto);
        BookingApi bookingApi = bookingService.createBooking(bookingDto);
        return ResponseEntity.ok().body(bookingApi);
    }


    @PostMapping("availability/search")
    public ResponseEntity<?> searchAvailability(@RequestBody SearchAvailbilityQuery query){
        log.info("Inside [/v2/bookings/availability/search], request to retrieve availability for booking");

        AvailabilityDto availabilityDto = bookingService.searchAvailability(query);

        return null;

    }

























    @PostMapping("/{booking_id}/cancel")
    public ResponseEntity<?> cancelPatient(@PathVariable String booking_id, @RequestBody CancelBookingDto cancelBookingDto){
        log.info("Inside [/v2/bookings/{booking_id}/cancel], request to cancel the booking");
        BookingApi tmp = bookingService.cancelBooking(booking_id,cancelBookingDto);
        return ResponseEntity.ok().body(tmp);
    }



    @PutMapping("/{booking_id}")
    public ResponseEntity<?> updateBooking(@PathVariable String booking_id, @RequestBody BookingDto bookingDto) {
        log.info("Inside [/v2/bookings/{booking_id}], request to update the booking");
        BookingApi bookingApi = bookingService.updateBooking(booking_id,bookingDto);
        return ResponseEntity.ok().body(BookingApiDto.from(bookingApi));
    }











}
