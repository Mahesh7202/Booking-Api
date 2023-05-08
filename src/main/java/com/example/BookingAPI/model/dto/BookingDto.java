package com.example.BookingAPI.model.dto;


import com.example.BookingAPI.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idempotency_key;
    private Booking booking;
}
