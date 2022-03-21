package com.shop.STYLE.GUIDE;

import com.shop.STYLE.GUIDE.repository.JdbcUserRepository;
import com.shop.STYLE.GUIDE.repository.JpaUserRepository;
import com.shop.STYLE.GUIDE.repository.UserRepository;
import com.shop.STYLE.GUIDE.repository.UserRepositoryInterface;
import com.shop.STYLE.GUIDE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

//    @Bean
    public UserService userService() {
        return new UserService(userRepositoryInterface());
    }

    @Bean
    public UserRepositoryInterface userRepositoryInterface() {
        return new JpaUserRepository(em);
    }
}
