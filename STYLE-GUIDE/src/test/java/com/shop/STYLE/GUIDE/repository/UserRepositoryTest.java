package com.shop.STYLE.GUIDE.repository;

import com.shop.STYLE.GUIDE.domain.tb_user_info;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    UserRepository repository = new UserRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void save() {
        //given
        tb_user_info user_info = new tb_user_info();
        user_info.setUSER_Id("spring");
        //when
        repository.save(user_info);
        //then
        tb_user_info result = repository.findById(user_info.getUSER_Id()).get();
        assertThat(result).isEqualTo(user_info);
    }

    @Test
    void findById() {
        //given
        tb_user_info member1 = new tb_user_info();
        member1.setUSER_Id("spring1");
        repository.save(member1);
        tb_user_info member2 = new tb_user_info();
        member2.setUSER_Id("spring2");
        repository.save(member2);
        //when
        tb_user_info result = repository.findById("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }
}