package com.example.BookingAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;



@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingCreatorDetails {


    @Column(name = "creator_type")
    private String creator_type;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "customer_id")
    private UUID customer_id;

    @Column(name = "team_member_id")
    private String team_member_id;
}
