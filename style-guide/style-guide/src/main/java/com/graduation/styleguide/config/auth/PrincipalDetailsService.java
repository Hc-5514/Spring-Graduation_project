package com.graduation.styleguide.config.auth;

import com.graduation.styleguide.domain.UserInfo;
import com.graduation.styleguide.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 스프링 시큐리티 로그인 요청시 자동으로 실행된다.
     * @param id
     * @return UserDetails. 리턴이 잘 되면 자동으로 UserDetails으로 세션을 생성한다.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserInfo userinfo = userRepository.findByUserID(id);

        if(userinfo == null) {
            return null;
        } else {
            return new PrincipalDetails(userinfo);
        }
    }
}
