package com.shop.STYLE.GUIDE.repository;

import com.shop.STYLE.GUIDE.domain.tb_user_info;

import java.util.Optional;

public interface UserRepositoryInterface {

    tb_user_info save (tb_user_info user_info);
    Optional<tb_user_info> findById(String id);
}
