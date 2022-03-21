package com.shop.STYLE.GUIDE.service;

import com.shop.STYLE.GUIDE.domain.tb_user_info;
import com.shop.STYLE.GUIDE.repository.UserRepository;
import com.shop.STYLE.GUIDE.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepositoryInterface userRepositoryInterface;

    // dependency injection
    @Autowired
    public UserService(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

//    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:sss");
//    Date time = new Date();
//    String localTime = format.format(time);

    /**
     * 회원가입
    */
    public String join(tb_user_info user_info){
        validateDuplicateId(user_info); // 중복 아이디 검증
//        user_info.setUser_AppendDate(localTime);
        userRepositoryInterface.save(user_info);
        return user_info.getUSER_Id();
    }

    private void validateDuplicateId(tb_user_info user_info) {
        userRepositoryInterface.findById(user_info.getUSER_Id())
                .ifPresent(m -> { // Null Error 방지
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
    }

    public Optional<tb_user_info> findOne(String id){
        return userRepositoryInterface.findById(id);
    }

}
