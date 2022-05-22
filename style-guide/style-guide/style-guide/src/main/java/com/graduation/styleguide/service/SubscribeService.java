package com.graduation.styleguide.service;

import com.graduation.styleguide.dto.SubscribeDto;
import com.graduation.styleguide.handler.CustomApiException;
import com.graduation.styleguide.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional
    public void follow(String stylelistId, String userId) {
        if(subscribeRepository.findSubscribeByStylelistIdAndUserId(stylelistId, userId) != null) throw new CustomApiException("이미 팔로우 하였습니다.");
        subscribeRepository.subscribe(stylelistId, userId);
    }

    @Transactional
    public void unFollow(String stylelistId, String userId) {
        subscribeRepository.unSubscribe(stylelistId, userId);
    }

    @Transactional
    public List<SubscribeDto> getFollower(long stylelistId, long userId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.from_user_id AND f.to_user_id = ?");
        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, userId)
                .setParameter(2, userId)
                .setParameter(3, stylelistId);

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtoList = result.list(query, SubscribeDto.class);
        return subscribeDtoList;
    }

    @Transactional
    public List<SubscribeDto> getFollowing(long profileId, long loginId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.to_user_id AND f.from_user_id = ?");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, loginId)
                .setParameter(3, profileId);

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtoList = result.list(query, SubscribeDto.class);
        return subscribeDtoList;
    }

}
