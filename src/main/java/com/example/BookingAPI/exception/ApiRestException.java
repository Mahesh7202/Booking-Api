package com.example.BookingAPI.exception;

import com.example.BookingAPI.model.Enum.Category;
import com.example.BookingAPI.model.Enum.Code;
import com.example.BookingAPI.model.Errors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ApiRestException extends RuntimeException{
    public ApiRestException(Errors errors) {
        super(errors);
    }
}
