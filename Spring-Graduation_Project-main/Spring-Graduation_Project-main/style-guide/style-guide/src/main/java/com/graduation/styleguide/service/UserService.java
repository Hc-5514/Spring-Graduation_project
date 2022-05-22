package com.graduation.styleguide.service;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.domain.UserInfo;
import com.graduation.styleguide.dto.UserInfoDto;
import com.graduation.styleguide.dto.UserSignupDto;
import com.graduation.styleguide.dto.UserUpdateDto;
import com.graduation.styleguide.handler.CustomValidationException;
import com.graduation.styleguide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {  //implements UserDetailsService

    private final UserRepository userRepository;

    /**
     * 회원정보 저장
     *
     * @param userSignupDto 회원 가입정보가 들어있는 DTO
     * @return 저장되는 회원의 PK
     */
    @Transactional
    public UserInfo save(UserSignupDto userSignupDto) {
        if(userRepository.findByUserID(userSignupDto.getUserID()) != null) throw new CustomValidationException("이미 존재하는 아이디입니다.");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //userInfoDto.setPassword(encoder.encode(userInfoDto.getPassword()));

        return userRepository.save(UserInfo.builder()
                .userID(userSignupDto.getUserID())
                .password(encoder.encode(userSignupDto.getPassword()))
                //.password(userInfoDto.getPassword()).build()).getCode();
                .name(userSignupDto.getName())
                .auth("ROLE_USER")
                .email(userSignupDto.getEmail())
                .contact(userSignupDto.getContact())
                .address(null)
                .build());
    }

    @Transactional
    public UserInfoDto getUserInfoDto(long code, long sessionId) {

        UserInfoDto userInfoDto = new UserInfoDto();

        UserInfo userInfo = userRepository.findById(code).orElseThrow(() -> { return new CustomValidationException("찾을 수 없는 user입니다.");});
        userInfoDto.setUserInfo(userInfo);

        // loginEmail 활용하여 currentId가 로그인된 사용자 인지 확인
        UserInfo loginUser = userRepository.findById(sessionId).orElseThrow(() -> { return new CustomValidationException("찾을 수 없는 user입니다.");});
        userInfoDto.setLoginUser(loginUser.getCode() == userInfo.getCode());

        return userInfoDto;
    }

    @Transactional
    public void update(UserUpdateDto userUpdateDto, MultipartFile multipartFile, PrincipalDetails principalDetails){
        UserInfo userInfo = userRepository.findById(principalDetails.getUserInfo().getCode()).orElseThrow(() -> { return new CustomValidationException("찾을 수 없는 user입니다.");});
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userInfo.update(
                encoder.encode(userUpdateDto.getPassword()),
                userUpdateDto.getName(),
                userUpdateDto.getEmail(),
                userUpdateDto.getContact(),
                userUpdateDto.getAddress()
        );

        //세션 정보 변경
        principalDetails.updateUser(userInfo);
    }
}
