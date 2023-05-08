package com.example.BookingAPI.model.dto;

import com.example.BookingAPI.model.Errors;
import com.example.BookingAPI.model.availability.Availability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDto {

    private List<Availability> availabilities;
    private List<Errors> errors;


}
