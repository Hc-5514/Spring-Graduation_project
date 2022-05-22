package com.graduation.styleguide.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@Getter
@Data
public class BusinessInfoDto {

    @Size(min=2, max=20, message = "사업자등록번호는 숫자로만 입력해주세요.")
    @NotBlank(message = "사업자등록번호를 입력해주세요")
    private String businessNumber;

    @Size(min=2, max=20, message = "대표자명은 2글자 이상, 20글자 이내로 작성해 주세요.")
    @NotBlank(message = "대표자명을 입력해주세요")
    private String ceo;

    @Size(min=2, max=20, message = "상호명(단체명)은 2글자 이상, 20글자 이내로 작성해 주세요.")
    @NotBlank(message = "상호(단체명)을 입력해주세요")
    private String businessName;

    @Size(min=2, max=20, message = "전화번호는 2글자 이상, 20글자 이내로 작성해 주세요.")
    @NotBlank(message = "전화번호를 입력해주세요")
    private String businessContact;

    @Size(min=2, max=100, message = "사업장 소재지는 2글자 이상, 20글자 이내로 작성해 주세요.")
    @NotBlank(message = "사업장 소재지를 입력해주세요")
    private String businessAddress;
}
