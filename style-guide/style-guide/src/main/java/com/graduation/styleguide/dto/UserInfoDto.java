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
    private UserInfo userInfo;

}
