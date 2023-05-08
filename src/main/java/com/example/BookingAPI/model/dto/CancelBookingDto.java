package com.example.BookingAPI.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelBookingDto {

    private String idempotency_key;
    private Integer booking_version;
}
