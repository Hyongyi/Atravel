package com.example.demo.domain.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TravelInfo")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TravelInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String bookingno;

    @Column(length = 200, nullable = false)
    private String airlines;


    @Column(length = 200, nullable = false)
    private String departlocation;

    @Column(length = 200, nullable = false)
    private String arrivallocation;


    @Column(name = "departtime")
    private java.sql.Time departtime;

    @Column(name = "arrivaltime")
    private java.sql.Time arrivaltime;


    @Column(length = 200, nullable = false)
    private String departdate;

    @Column(length = 200, nullable = false)
    private String arrivaldate;

    @Column(length = 200, nullable = true)
    private String returndate;


//    @Column(name = "departdate")
//    private java.sql.Date departdate;
//
//    @Column(name = "arrivaldate")
//    private java.sql.Date arrivaldate;


    @Column(length = 200, nullable = false)
    private String flightno;

    @Column(length = 200, nullable = false)
    private Integer flighthour;


    @Column(length = 200, nullable = false)
    private String gate;

    @Column(length = 200, nullable = false)
    private String seat;

    @Column(length = 200, nullable = false)
    private Integer price;



}
