package com.graduation.styleguide.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="subscribe")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "stylelist_id")
    @ManyToOne
    private UserInfo stylelistId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserInfo userId;

    @Builder
    public Subscribe(UserInfo stylelistId, UserInfo userId){
        this.stylelistId = stylelistId;
        this.userId = userId;
    }

}
