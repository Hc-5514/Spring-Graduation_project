package com.graduation.styleguide.repository;

import com.graduation.styleguide.domain.Subscribe;
import com.graduation.styleguide.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    Subscribe findSubscribeByStylelistIdAndUserId(String stylelistId, String userId);

    // 구독하는 스타일리스트 수
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE stylelist_id = :profileId", nativeQuery = true)
    int findSubscribeCountById(String profileId);

    // 스타일리스트의 구독자 수
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE user_id = :profileId", nativeQuery = true)
    int findSubscriberCountById(String profileId);

    @Modifying
    @Query(value = "INSERT INTO subscribe(stylelist_id, user_id) VALUES(:stylelistId, :userId)", nativeQuery = true)
    void subscribe(String stylelistId, String userId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE stylelist_id = :stylelistId AND user_id = :userId", nativeQuery = true)
    void unSubscribe(String stylelistId, String userId);
}
