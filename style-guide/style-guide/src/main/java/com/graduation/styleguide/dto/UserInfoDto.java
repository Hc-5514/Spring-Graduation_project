package com.graduation.styleguide.dto;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {

    private String email;
    private String password;
    private String auth;
=======
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

>>>>>>> e3f1920be13dbcfb142664a10a3bdb0c798d58c6
}
