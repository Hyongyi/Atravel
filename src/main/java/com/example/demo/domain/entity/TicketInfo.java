package com.example.demo.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TicketInfo")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TicketInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String first_name;

    @Column(length = 200, nullable = false)
    private String last_name;

    @Column(length = 200, nullable = false)
    private String nationality;

    @Column(length = 200, nullable = false)
    private String passportno;

    @Column(length = 200, nullable = false)
    private String passportexpire;

    @Column(nullable = false)
    private String age;

    @Column(length = 200, nullable = false)
    private String sex;

    @Column(length = 200, nullable = false)
    private String phonenum;

    @Column(length = 200, nullable = false)
    private String userinfo_id;


    @Column(length = 200, nullable = false)
    private long travelinfo_boookingno;


}