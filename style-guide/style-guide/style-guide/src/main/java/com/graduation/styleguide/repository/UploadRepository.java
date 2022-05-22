package com.graduation.styleguide.repository;

import com.graduation.styleguide.domain.UploadInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UploadRepository extends JpaRepository<UploadInfo, Long> {
//    UploadInfo findByName(String product_name);
}
