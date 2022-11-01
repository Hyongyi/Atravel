package com.example.demo.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "UserInfo")
@Getter
@Setter

public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserNum;

    @Column(length = 200, nullable = false)
    private String id;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String age;

    @Column(length = 200, nullable = false)
    private String sex;

    @Column(length = 200, nullable = false)
    private String addressCode;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(length = 200, nullable = false)
    private String addressDetail;

    @Column(length = 200, nullable = true)
    private String addressRef;




    @Builder
    public UserInfo(Long UserNum,String id, String password, String name, String age, String sex, String addressCode, String address, String addressDetail, String addressRef) {
        this.UserNum = UserNum;
        this.id = id;
        this.name = name;
        this.password = password;
        this.age =age;
        this.sex = sex;
        this.addressCode = addressCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.addressRef = addressRef;


    }
}