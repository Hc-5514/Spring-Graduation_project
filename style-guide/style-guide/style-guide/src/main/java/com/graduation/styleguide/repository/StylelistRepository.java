package com.graduation.styleguide.repository;


import com.graduation.styleguide.domain.UploadInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StylelistRepository extends JpaRepository<UploadInfo, Long> {

    @Query(value = "SELECT * FROM clothes WHERE id = :stylelistId", nativeQuery = true)
    List<UploadInfo> findIdxbyStylelistId(String stylelistId);

    @Query(value = "SELECT id FROM clothes WHERE idx = :Idx", nativeQuery = true)
    String findStylelistIdbyIdx(Long Idx);
}
