package com.example.BookingAPI.exception;

import com.example.BookingAPI.model.Errors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.BookingAPI.model.Enum.Category.INVALID_REQUEST_ERROR;
import static com.example.BookingAPI.model.Enum.Code.MISSING_REQUIRED_PARAMETER;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Errors errors = new Errors(INVALID_REQUEST_ERROR, MISSING_REQUIRED_PARAMETER, "Booking data Not Found", "booking");

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ApiRestException.class})
    public ResponseEntity<Object> handle(Exception e){
        return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
    }
}
