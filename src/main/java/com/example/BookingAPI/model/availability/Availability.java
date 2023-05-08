package com.example.BookingAPI.model.availability;

import com.example.BookingAPI.model.AppointmentSegments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Availability {

    @Embedded
    @ElementCollection
    @CollectionTable(name="BookingApi_Appointment_segments",joinColumns=@JoinColumn(name="id"))
    @Column(name = "appointment_segments")
    private List<AppointmentSegments> appointment_segments;

    @Column(name = "location_id")
    private String location_id;

    @Column(name = "start_at")
    private String start_at;
}
