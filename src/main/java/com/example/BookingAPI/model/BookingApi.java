package com.example.BookingAPI.model;


import com.example.BookingAPI.model.dto.BookingApiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingApi {

    private String idempotency_key;

    private Booking booking;

    public BookingApi(Booking booking) {
        this.booking = booking;
    }

    private List<Errors> errors;

    public static BookingApi from(BookingApiDto bookingApiDto){
        ModelMapper modelMapper = new ModelMapper();
        BookingApi bookingApi = modelMapper.map(bookingApiDto, BookingApi.class);
        return bookingApi;
    }
}
