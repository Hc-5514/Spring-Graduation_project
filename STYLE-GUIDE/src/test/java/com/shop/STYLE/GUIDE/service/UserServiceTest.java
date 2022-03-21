package com.shop.STYLE.GUIDE.service;

import com.shop.STYLE.GUIDE.domain.tb_user_info;
import com.shop.STYLE.GUIDE.repository.UserRepository;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    UserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach(){
        userRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        tb_user_info user_info = new tb_user_info();
        user_info.setUSER_Id("spring");

        //when
        String saveId = userService.join(user_info);

        //then
        tb_user_info findId = userService.findOne(saveId).get();
        assertThat(user_info.getUSER_Id()).isEqualTo(findId.getUSER_Id());

/*        //given
        tb_user_info user_info = new tb_user_info();
        user_info.setUSER_Id("spring");
        user_info.setUSER_Pw("0101");
        user_info.setUSER_Name("test01");

        //when


        //then*/

    }

    @Test
    public void 중복_아이디_예외(){
        //given
        tb_user_info user_info1 = new tb_user_info();
        user_info1.setUSER_Id("spring");

        tb_user_info user_info2 = new tb_user_info();
        user_info2.setUSER_Id("spring");

        //when
        userService.join(user_info1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user_info2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
/*
        try{
            userService.join(user_info2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
        }
*/

        //then
    }
}