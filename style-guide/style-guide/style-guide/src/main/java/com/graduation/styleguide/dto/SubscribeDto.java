package com.graduation.styleguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigInteger;

@Builder
@AllArgsConstructor
@Getter
@Data
public class SubscribeDto {

    private long id;
    private String name;
    private int followState;
    private int loginUser;

    public SubscribeDto(BigInteger id, String name, int followState, int loginUser) {
        this.id = id.longValue();
        this.name = name;
        this.followState = followState;
        this.loginUser = loginUser;
    }
}
