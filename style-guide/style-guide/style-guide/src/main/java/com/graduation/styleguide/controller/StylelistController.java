package com.graduation.styleguide.controller;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.domain.UploadInfo;
import com.graduation.styleguide.dto.StylelistDto;
import com.graduation.styleguide.dto.UserInfoDto;
import com.graduation.styleguide.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        List<UploadInfo> stylelistDto = userService.getClothesListInfoDto(userInfoDto.getUserInfo().getUserID());
        model.addAttribute("userInfoDto", userInfoDto);
        model.addAttribute("stylelistDto", stylelistDto);
        return "layout/StylelistPage";
    }

    // 상품 페이지
    @GetMapping("/clothesdetails")
    public String ClothesDetails(Model model, @RequestParam long id) {
        StylelistDto stylelistDto = userService.getClothesInfoDto(id);
        model.addAttribute("stylelistDto", stylelistDto);
        return "layout/ClothesDetails";
    }
}
