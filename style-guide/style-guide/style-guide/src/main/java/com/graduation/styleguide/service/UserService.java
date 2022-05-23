package com.graduation.styleguide.service;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.domain.UserInfo;
import com.graduation.styleguide.dto.BusinessInfoDto;
import com.graduation.styleguide.dto.UserInfoDto;
import com.graduation.styleguide.dto.UserSignupDto;
import com.graduation.styleguide.dto.UserUpdateDto;
import com.graduation.styleguide.handler.CustomValidationException;
import com.graduation.styleguide.repository.SubscribeRepository;
import com.graduation.styleguide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {  //implements UserDetailsService

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

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
    public UserInfoDto getUserInfoDto(String userId, String sessionId) {

        UserInfoDto userInfoDto = new UserInfoDto();

        UserInfo userInfo = userRepository.findById(userId).orElseThrow(() -> { return new CustomValidationException("찾을 수 없는 user입니다.");});
        userInfoDto.setUserInfo(userInfo);

        // loginID 활용하여 currentId가 로그인된 사용자 인지 확인
        UserInfo loginUser = userRepository.findById(sessionId).orElseThrow(() -> { return new CustomValidationException("찾을 수 없는 user입니다.");});
        userInfoDto.setLoginUser(loginUser.getUserID() == userInfo.getUserID());

        // currentId를 가진 user가 loginId를 가진 user를 구독 했는지 확인
        userInfoDto.setSubscribe(subscribeRepository.findSubscribeByStylelistIdAndUserId(loginUser.getUserID(), userInfo.getUserID()) != null);

        //currentId를 가진 user의 구독자, 구독중인 스타일리스트 수를 확인한다.
        userInfoDto.setUserSubscriberCount(subscribeRepository.findSubscriberCountById(userId));
        userInfoDto.setUserSubscribeCount(subscribeRepository.findSubscribeCountById(userId));

        return userInfoDto;
    }

    @Transactional
    public void update(UserUpdateDto userUpdateDto, PrincipalDetails principalDetails){
        UserInfo userInfo = userRepository.findById(principalDetails.getUserInfo().getUserID()).orElseThrow(() -> { return new CustomValidationException("찾을 수 없는 user입니다.");});
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

    @Transactional
    public void updateBusinessInfo(BusinessInfoDto businessInfoDto, PrincipalDetails principalDetails){
        UserInfo userInfo = userRepository.findById(principalDetails.getUserInfo().getUserID()).orElseThrow(() -> { return new CustomValidationException("찾을 수 없는 user입니다.");});

        userInfo.updateBusinessInfo(
                "ROLE_ADMIN,ROLE_USER",
                businessInfoDto.getBusinessNumber(),
                businessInfoDto.getCeo(),
                businessInfoDto.getBusinessName(),
                businessInfoDto.getBusinessContact(),
                businessInfoDto.getBusinessAddress()
        );

        // 세션 정보 변경
        principalDetails.updateUser(userInfo);

        // 로그인 계정 권한 변경
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());

        updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}