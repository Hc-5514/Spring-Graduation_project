package com.graduation.styleguide.repository;

import com.graduation.styleguide.domain.UploadInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UploadRepository extends JpaRepository<UploadInfo, Long> {
//    UploadInfo findByName(String product_name);
    @Query(value = "SELECT * FROM clothes WHERE product_name LIKE :product_name OR product_name LIKE CONCAT('%,',:product_name,',%') OR product_name LIKE CONCAT('%,',:product_name) " + "OR product_name LIKE CONCAT(:product_name,',%')", nativeQuery = true)
    List<UploadInfo> searchResult(String product_name, Pageable list);
}
