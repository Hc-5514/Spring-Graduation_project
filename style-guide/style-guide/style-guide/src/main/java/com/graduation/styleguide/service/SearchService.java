package com.graduation.styleguide.service;

import com.graduation.styleguide.domain.UploadInfo;
import com.graduation.styleguide.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final UploadRepository uploadRepository;


    @Transactional
    public List<UploadInfo> getPost(String product_name, Pageable list) {
        List<UploadInfo> uploadInfoList = uploadRepository.searchResult(product_name, list);

        return uploadInfoList;
    }
//    public List<UploadInfo> findByProductContains(String search) {
//        return uploadRepository.findByproductContains(search);
//    }
//
//    public int countByProductContains(String search) {
//        return uploadRepository.countByProductContains(search);
//    }
}
