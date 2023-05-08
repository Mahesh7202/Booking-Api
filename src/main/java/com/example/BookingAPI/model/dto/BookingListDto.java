package com.example.BookingAPI.model.dto;

import com.example.BookingAPI.model.Booking;
import com.example.BookingAPI.model.Errors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingListDto {
    private List<Booking> booking;
    private String cursor;
    private List<Errors> errors;

    public BookingListDto(List<Booking> booking, String cursor) {
        this.booking = booking;
        this.cursor = cursor;
    }
}
