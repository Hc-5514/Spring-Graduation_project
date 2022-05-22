package com.graduation.styleguide.config;

import com.graduation.styleguide.config.auth.PrincipalDetailsService;
import com.graduation.styleguide.config.oauth.Oauth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity  // Security 사용
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //private final UserService userService;
    private final PrincipalDetailsService principalDetailsService;
    private final Oauth2DetailsService oauth2DetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception { // http 관련 인증 설정
        http.csrf().disable();
        http.authorizeRequests() // 접근에 대한 인증 설정
                .antMatchers("/login", "/signup", "/user", "/", "/saledetails", "/salepage", "/adminpage/**", "/assets/**", "/css/**", "/img/**"
                        , "/js/**", "/main/**", "/salepage/**", "/style/**").permitAll() // 누구나 접근 허용
                .antMatchers("/profile", "/test2","/admin").hasRole("USER") // USER, ADMIN만 접근 가능
                //.antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
            .and()
                .formLogin() // 로그인에 관한 설정
                .loginPage("/login") // 로그인 페이지 링크
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
            .and()
                .logout() // 로그아웃
                .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
            .and()
                .oauth2Login() //form 로그인도 하고, oauth2로그인도 한다.
                .loginPage("/login")//내가 만든 로그인 페이지 사용
                .userInfoEndpoint() //oauth2로그인시 최종 유저의 정보를 바로 받아온다.
                .userService(oauth2DetailsService)
        ;
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 필요한 정보들을 가져오는 곳
//        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder()); // 해당 서비스(userService)에서는 UserDetailsService를 implements해서 loadUserByUsername() 구현해야함 (서비스 참고)
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
