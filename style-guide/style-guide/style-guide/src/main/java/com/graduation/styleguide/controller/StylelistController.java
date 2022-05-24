package com.graduation.styleguide.controller;

import com.graduation.styleguide.domain.UploadInfo;
import com.graduation.styleguide.dto.StylelistDto;
import com.graduation.styleguide.dto.UserInfoDto;
import com.graduation.styleguide.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StylelistController {

    private final UserService userService;

    // 스타일리스트 옷장 화면으로 이동
    @GetMapping("/stylelistpage")
    public String StylelistPage(Model model, @RequestParam String id) {

        UserInfoDto userInfoDto = userService.getStylelistInfoDto(id);
        model.addAttribute("userInfoDto", userInfoDto);
        System.out.println("userInfoDto: " + userInfoDto.getUserInfo().getUserID());

        List<UploadInfo> stylelistDtoList = userService.getClothesListInfoDto(userInfoDto.getUserInfo().getUserID());
        model.addAttribute("stylelistDtoList", stylelistDtoList);
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getId());
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getIdx());
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getPic_name());
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getProduct_name());
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getProduct_count());
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getProduct_price());
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getProduct_intro());

        return "layout/StylelistPage";
    }

    // 상품 페이지
    @GetMapping("/clothesdetails")
    public String ClothesDetails(Model model, @RequestParam long id) {

        StylelistDto stylelistDto = userService.getClothesInfoDto(id);
        model.addAttribute("stylelistDto", stylelistDto);
        System.out.println("stylelistDto: " + stylelistDto.getUploadInfo().getId());
        System.out.println("stylelistDto: " + stylelistDto.getUploadInfo().getIdx());
        System.out.println("stylelistDto: " + stylelistDto.getUploadInfo().getProduct_name());

        String stylelistId = userService.getStylelistIdDto(id);
        System.out.println("stylelistId: " + stylelistId);
        List<UploadInfo> stylelistDtoList = userService.getClothesListInfoDto(stylelistId);
        model.addAttribute("stylelistDtoList", stylelistDtoList);
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getId());
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getIdx());
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getProduct_name());

        return "layout/ClothesDetails";
    }
}
