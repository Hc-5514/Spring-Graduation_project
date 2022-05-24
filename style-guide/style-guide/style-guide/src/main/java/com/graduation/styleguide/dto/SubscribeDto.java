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

    private long idx;
    private String name;
    private int subscribeState;
    private int loginUser;

    public SubscribeDto(BigInteger idx, String name, int subscribeState, int loginUser) {
        this.idx = idx.longValue();
        this.name = name;
        this.subscribeState = subscribeState;
        this.loginUser = loginUser;
    }
}
