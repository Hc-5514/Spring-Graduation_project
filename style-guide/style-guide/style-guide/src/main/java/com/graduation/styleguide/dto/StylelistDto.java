package com.graduation.styleguide.dto;

import com.graduation.styleguide.domain.UploadInfo;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class StylelistDto {

    private UploadInfo uploadInfo;
}
