package com.example.BookingAPI.model;

import com.example.BookingAPI.model.Enum.LocationType;
import com.example.BookingAPI.model.Enum.Source;
import com.example.BookingAPI.model.Enum.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Booking {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "all_day")
    private Boolean all_day;

    @Embedded
    @ElementCollection
    @CollectionTable(name="BookingApi_Appointment_segments",joinColumns=@JoinColumn(name="id"))
    @Column(name = "appointment_segments")
    private List<AppointmentSegments> appointment_segments;

    @DateTimeFormat
    @CreationTimestamp
    @Column(name = "created_at")
    private String created_at;

    @Embedded
    @Column(name = "creator_details")
    private BookingCreatorDetails creator_details;

    @Column(name = "customer_id",insertable = false,updatable = false)
    private String customer_id;

    @Column(name = "customer_note")
    private String customer_note;

    @Column(name = "location_id")
    private String location_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private LocationType location_type;

    @Column(name = "seller_note")
    private String seller_note;

    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private Source source;

    @Column(name = "start_at")
    private String start_at;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "transition_time_minutes")
    private Integer transition_time_minutes;

    @DateTimeFormat
    @CreationTimestamp
    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "version")
    private Integer version;


}
