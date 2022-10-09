package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;


@Getter
@Setter
@ToString
@NoArgsConstructor

public class TravelDTO {

    private Long id;
    private String bookingno;
    private String airlines;
    private String departlocation;
    private String arrivallocation;
    private java.sql.Time departtime;
    private java.sql.Time arrivaltime;
    private String departdate;
    private String arrivaldate;
    private String returndate;
    private String flightno;
    private Integer flighthour;
    private String gate;
    private String seat;
    private Integer price;





}
