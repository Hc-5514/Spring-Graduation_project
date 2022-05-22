package com.graduation.styleguide.repository;

import com.graduation.styleguide.domain.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserInfo user;

    @BeforeEach
    public void setUp(){
        user = UserInfo.builder().userID("test").password("test").name("chan").auth("ROLE_USER").email("test@naver.com").contact("01012345678").address(null).build();
    }

    @AfterEach
    public void tearDown() {userRepository.deleteAll();}

    @Test
    public void findByUserID_성공(){
        // given
        userRepository.save(user);

        // when
        UserInfo result = userRepository.findByUserID("test");

        // then
        assertThat(result.getUserID()).isEqualTo(user.getUserID());
    }
}