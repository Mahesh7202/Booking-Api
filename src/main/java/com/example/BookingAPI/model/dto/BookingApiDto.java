package com.example.BookingAPI.model.dto;

import com.example.BookingAPI.model.Booking;
import com.example.BookingAPI.model.BookingApi;
import com.example.BookingAPI.model.Errors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingApiDto {
    private Booking booking;
    private List<Errors> errors;


    public BookingApiDto(Booking booking) {
        this.booking = booking;
    }

    public static BookingApiDto from(BookingApi bookingApi){
        ModelMapper modelMapper = new ModelMapper();
        BookingApiDto bookingApiDto = modelMapper.map(bookingApi, BookingApiDto.class);
        return bookingApiDto;
    }
}
