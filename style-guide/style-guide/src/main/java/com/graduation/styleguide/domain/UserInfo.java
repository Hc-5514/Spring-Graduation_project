package com.graduation.styleguide.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo implements UserDetails {
=======

import javax.persistence.*;

@Entity(name="user_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {
>>>>>>> e3f1920be13dbcfb142664a10a3bdb0c798d58c6

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long code;

<<<<<<< HEAD
    @Column(name = "email", unique = true)
    private String email;
=======
    @Column(name = "id", unique = true)
    private String userID;
>>>>>>> e3f1920be13dbcfb142664a10a3bdb0c798d58c6

    @Column(name = "password")
    private String password;

<<<<<<< HEAD
    @Column(name = "auth")
    private String auth;

    @Builder
    public UserInfo(String email, String password, String auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    // 사용자의 권한을 콜렉션 형태로 반환
    // 단, 클래스 자료형은 GrantedAuthority를 구현해야함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();

        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;
    }

    // 사용자의 id를 반환 (unique한 값)
    @Override
    public String getUsername() {
        return email;
    }

    // 사용자의 password를 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
=======
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

    @Column(name = "stylelist_id", unique = true)
    private String stylelistId;

    @Builder
    public UserInfo(String userID, String password, String name, String auth, String email, String contact, String address, String stylelistId) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.auth = auth;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.stylelistId = stylelistId;
    }

    // profile 에서 개인정보 수정
    public void update(String password, String name, String email, String contact, String address){
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
>>>>>>> e3f1920be13dbcfb142664a10a3bdb0c798d58c6
    }
}
