package com.shop.STYLE.GUIDE.repository;

import com.shop.STYLE.GUIDE.domain.tb_user_info;

import javax.sql.DataSource;
import java.util.Optional;

public class JdbcUserRepository implements UserRepositoryInterface {

    private final DataSource dataSource;

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public tb_user_info save(tb_user_info user_info) {
        return null;
    }

    @Override
    public Optional<tb_user_info> findById(String id) {
        return Optional.empty();
    }
}
