package com.graduation.styleguide.repository;

import com.graduation.styleguide.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
<<<<<<< HEAD
    Optional<UserInfo> findByEmail(String email);
=======
//    Optional<UserInfo> findByUserID(String userID);
    UserInfo findByUserID(String userID);
>>>>>>> e3f1920be13dbcfb142664a10a3bdb0c798d58c6
}
