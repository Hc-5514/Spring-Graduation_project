package com.shop.STYLE.GUIDE.repository;

import com.shop.STYLE.GUIDE.domain.tb_user_info;

import javax.persistence.EntityManager;
import java.util.Optional;

public class JpaUserRepository implements UserRepositoryInterface {

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public tb_user_info save(tb_user_info user_info) {
        em.persist(user_info);
        return user_info;
    }

    @Override
    public Optional<tb_user_info> findById(String id) {
        tb_user_info user_info = em.find(tb_user_info.class, id);
        return Optional.ofNullable(user_info);
    }
}
