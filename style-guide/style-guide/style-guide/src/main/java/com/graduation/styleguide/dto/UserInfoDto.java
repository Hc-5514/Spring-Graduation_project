package com.graduation.styleguide.dto;

import com.graduation.styleguide.domain.UserInfo;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class UserInfoDto {

    private boolean loginUser;
    private boolean subscribe;
    private UserInfo userInfo;
    // 구독자 수
    private int userSubscriberCount;
    // 구독중인 스타일리스트 수
    private int userSubscribeCount;

}
