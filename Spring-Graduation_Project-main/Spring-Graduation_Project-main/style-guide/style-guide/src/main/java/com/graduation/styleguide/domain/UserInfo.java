package com.graduation.styleguide.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="user_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {


    @Column(name = "code")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long code;

    @Id
    @Column(name = "id", unique = true)
    private String userID;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "auth")
    private String auth;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;


    @Builder
    public UserInfo(String userID, String password, String name, String auth, String email, String contact, String address) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.auth = auth;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    // profile 에서 개인정보 수정
    public void update(String password, String name, String email, String contact, String address){
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }
}
