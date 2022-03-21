package com.shop.STYLE.GUIDE.repository;

import com.shop.STYLE.GUIDE.domain.tb_user_info;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository implements UserRepositoryInterface {

    private static Map<String, tb_user_info> store = new HashMap<>();

    @Override
    public tb_user_info save(tb_user_info user_info) {
        store.put(user_info.getUSER_Id(), user_info);
        return user_info;
    }

    @Override
    public Optional<tb_user_info> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public void clearStore(){
        store.clear();
    }
}
