package com.example.BookingAPI.model;

import com.example.BookingAPI.model.Enum.Category;
import com.example.BookingAPI.model.Enum.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.function.Supplier;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Errors extends Throwable {


    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Code code;

    private String details;

    private String field;




}
