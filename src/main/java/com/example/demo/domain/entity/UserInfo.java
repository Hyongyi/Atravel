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

    @Column(nullable = false)
    private long age;

    @Column(length = 200, nullable = false)
    private String sex;


    @Builder
    public UserInfo(Long UserNum,String id, String password, String name, long age, String sex) {
        this.UserNum = UserNum;
        this.id = id;
        this.name = name;
        this.password = password;
        this.age =age;
        this.sex = sex;

    }
}