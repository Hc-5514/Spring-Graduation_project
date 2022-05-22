package com.graduation.styleguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Data
public class UploadPostDto {
    private String product_name;
    private String product_intro;
    private int product_count;
    private Long product_price;
//    private String pic_name;
}
