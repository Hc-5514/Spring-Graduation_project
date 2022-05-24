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
    private Long idx;

    @JoinColumn(name = "stylelist_idx")
    @ManyToOne
    private UserInfo stylelistIdx;

    @JoinColumn(name = "user_idx")
    @ManyToOne
    private UserInfo userIdx;

    @Builder
    public Subscribe(UserInfo stylelistIdx, UserInfo userIdx){
        this.stylelistIdx = stylelistIdx;
        this.userIdx = userIdx;
    }

}
