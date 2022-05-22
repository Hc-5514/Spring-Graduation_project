package com.graduation.styleguide.config.auth;

import com.graduation.styleguide.domain.UserInfo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private UserInfo userInfo;

    private Map<String, Object> attributes;

    public PrincipalDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public PrincipalDetails(UserInfo userInfo, Map<String, Object> attributes) {
        this.userInfo = userInfo;
    }

    public void updateUser(UserInfo userInfo) { this.userInfo = userInfo; }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // 사용자의 권한을 콜렉션 형태로 반환
    // 단, 클래스 자료형은 GrantedAuthority를 구현해야함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();

        for (String role : userInfo.getAuth().split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
