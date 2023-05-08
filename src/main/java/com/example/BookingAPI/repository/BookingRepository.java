package com.example.BookingAPI.repository;

import com.example.BookingAPI.model.Booking;
import com.example.BookingAPI.model.BookingApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
}
