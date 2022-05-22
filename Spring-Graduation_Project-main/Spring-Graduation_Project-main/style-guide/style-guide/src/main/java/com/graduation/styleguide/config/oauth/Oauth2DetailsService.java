package com.graduation.styleguide.config.oauth;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.domain.UserInfo;
import com.graduation.styleguide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class Oauth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); //유저 정보 가져온다.

        Map<String, Object> user_map = oAuth2User.getAttributes();
        String email = (String) user_map.get("email");
        String name = (String) user_map.get("name");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()); //랜덤한 비밀번호 생성

        UserInfo check_user = userRepository.findByUserID(email);
        if (check_user == null) { //최초 로그인
            UserInfo user = UserInfo.builder()
                    .email(email)
                    .password(password)
                    .contact(null)
                    .name(name)
                    .build();
            return new PrincipalDetails(userRepository.save(user), user_map); //oauth2로 로그인됬는지 구분할 수 있음.
        } else { //이미 회원가입 되어있음
            return new PrincipalDetails(check_user);
        }
    }
}
