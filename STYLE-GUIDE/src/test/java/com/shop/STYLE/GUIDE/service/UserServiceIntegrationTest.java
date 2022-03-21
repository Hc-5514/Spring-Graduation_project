package com.shop.STYLE.GUIDE.service;

import com.shop.STYLE.GUIDE.domain.tb_user_info;
import com.shop.STYLE.GUIDE.repository.UserRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired UserService userService;
    @Autowired UserRepositoryInterface userRepositoryInterface;

    @Test
    public void 회원가입() throws Exception {
        //given
        tb_user_info user_info = new tb_user_info();
        user_info.setUSER_Id("spring");

        //when
        String saveId = userService.join(user_info);

        //then
        tb_user_info findId = userRepositoryInterface.findById(saveId).get();
        assertEquals(user_info.getUSER_Id(), findId.getUSER_Id());
    }

    @Test
    public void 중복_아이디_예외() throws Exception {
        //given
        tb_user_info user_info1 = new tb_user_info();
        user_info1.setUSER_Id("spring");

        tb_user_info user_info2 = new tb_user_info();
        user_info2.setUSER_Id("spring");

        //when
        userService.join(user_info1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user_info2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }
}