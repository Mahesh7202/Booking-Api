package com.example.BookingAPI.model;


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
public class AppointmentSegments {

    @Column(name = "duration_minutes")
    private Integer duration_minutes;

    @Column(name = "service_variation_id")
    private String service_variation_id;

    @Column(name = "service_variation_version")
    private long service_variation_version;

    @Column(name = "team_member_id",insertable = false,updatable = false)
    private String team_member_id;

    @Column(name = "any_team_member")
    private Boolean any_team_member;

    @Column(name = "intermission_minutes")
    private Integer intermission_minutes;

    @Column(name = "resource_ids")
    private String[] resource_ids;



}
