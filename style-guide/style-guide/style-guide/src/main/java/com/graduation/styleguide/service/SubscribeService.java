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
    public void follow(String userIdx, String stylelistIdx) {

        if(subscribeRepository.findSubscribeByStylelistIdxAndUserIdx(userIdx, stylelistIdx) != 0) throw new CustomApiException("이미 구독하였습니다.");
        subscribeRepository.subscribe(userIdx, stylelistIdx);
    }

    @Transactional
    public void unFollow(String userIdx, String stylelistIdx) {
        subscribeRepository.unSubscribe(userIdx, stylelistIdx);
    }

    @Transactional
    public List<SubscribeDto> getFollower(long stylelistIdx, long userIdx) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.idx, u.name, ");
        sb.append("if ((SELECT 1 FROM subscribe WHERE userIdx = ? AND stylelist_idx = u.id), TRUE, FALSE) AS subscribeState, ");
        sb.append("if ((?=u.idx), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user_info u, subscribe f ");
        sb.append("WHERE u.idx = f.user_idx AND f.stylelist_idx = ?");
        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, userIdx)
                .setParameter(2, userIdx)
                .setParameter(3, stylelistIdx);

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtoList = result.list(query, SubscribeDto.class);
        return subscribeDtoList;
    }

    @Transactional
    public List<SubscribeDto> getFollowing(long profileId, long loginId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.name, ");
        sb.append("if ((SELECT 1 FROM subscribe WHERE userIdx = ? AND stylelist_idx = u.id), TRUE, FALSE) AS subscribeState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user_info u, subscribe f ");
        sb.append("WHERE u.id = f.stylelist_idx AND f.userIdx = ?");

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
