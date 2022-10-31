package com.example.demo.dto;


import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class TicketDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private String nationality;
    private String passportno;
    private String passportexpire;
    private String sex;
    private String phonenum;
    private String age;
    private String userinfo_id;  // 유저인포의 id 값 결제자 정보
    private long travelinfo_boookingno;  //비행기 티켓의 내용


}
