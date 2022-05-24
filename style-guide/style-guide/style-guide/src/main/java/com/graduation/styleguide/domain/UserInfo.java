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

    @Column(name = "code", unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long code;

    @Id
    @Column(name = "id")
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

    @Column(name = "business_number")
    private String businessNumber;

    @Column(name = "ceo")
    private String ceo;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_contact")
    private String businessContact;

    @Column(name = "business_address")
    private String businessAddress;

    @Builder
    public UserInfo(String userID, String password, String name, String auth, String email, String contact, String address,
                    String businessNumber, String ceo, String businessName, String businessContact, String businessAddress) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.auth = auth;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.businessNumber = businessNumber;
        this.ceo = ceo;
        this.businessName = businessName;
        this.businessContact = businessContact;
        this.businessAddress = businessAddress;
    }

    // profile 에서 개인정보 수정
    public void update(String password, String name, String email, String contact, String address){
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    public void updateBusinessInfo(String auth, String businessNumber, String ceo, String businessName, String businessContact, String businessAddress){
        this.auth = auth;
        this.businessNumber = businessNumber;
        this.ceo = ceo;
        this.businessName = businessName;
        this.businessContact = businessContact;
        this.businessAddress = businessAddress;
    }
}
