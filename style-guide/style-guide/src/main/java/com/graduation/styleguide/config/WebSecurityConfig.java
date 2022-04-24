package com.graduation.styleguide.config;

import com.graduation.styleguide.service.UserService;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity  // Security 사용
@Configuration
public class WebSecurityConfig  {    // extends WebSecurityConfigurerAdapter

    private final UserService userService;

    @Builder
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

//    @Override
//    public void configure(WebSecurity web) { // static 하위 파일 목록(css, js, img) 인증 무시
//        web.ignoring().antMatchers("/favicon.ico", "/error", "/img/**", "/css/**", "/js/**");
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/favicon.ico", "/error", "/adminpage/**", "/assets/**", "/css/**", "/img/**"
        , "/js/**", "/main/**", "/salepage/**", "/style/**");
//        return (web) -> web.ignoring().antMatchers("/favicon.ico", "/error", "css/**", "");
//        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception { // http 관련 인증 설정
//        http
//                .authorizeRequests() // 접근에 대한 인증 설정
//                .antMatchers("/", "/login", "/signup").permitAll() // 누구나 접근 허용
//                .antMatchers("/profile").hasRole("USER") // USER, ADMIN만 접근 가능
////                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
//                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
//                .and()
//                .formLogin() // 로그인에 관한 설정
//                .loginPage("/login") // 로그인 페이지 링크
//                .defaultSuccessUrl("/test2") // 로그인 성공 후 리다이렉트 주소
//                .and()
//                .logout() // 로그아웃
//                .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
//                .invalidateHttpSession(true) // 세션 날리기
//        ;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) ->
        {
            try {
                requests.antMatchers("/", "/login", "/signup").permitAll() // 누구나 접근 허용
                        .antMatchers("/profile", "/test2").hasRole("USER") // USER, ADMIN만 접근 가능
                        .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
//                        .failureUrl("/")
                        .and()
                        .logout()
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return http.build();
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 필요한 정보들을 가져오는 곳
//        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder()); // 해당 서비스(userService)에서는 UserDetailsService를 implements해서 loadUserByUsername() 구현해야함 (서비스 참고)
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
