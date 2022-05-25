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
    public String StylelistPage(Model model, @RequestParam String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // 해당 페이지의 스타일리스트 반환
        UserInfoDto stylelistDto = userService.getStylelistInfoDto(id);
        model.addAttribute("stylelistDto", stylelistDto);
        System.out.println("stylelistDto: " + stylelistDto.getUserInfo().getUserID());

        // 해당 스타일리스트의 옷 반환
        List<UploadInfo> stylelistDtoList = userService.getClothesListInfoDto(stylelistDto.getUserInfo().getUserID());
        model.addAttribute("stylelistDtoList", stylelistDtoList);
        System.out.println("stylelistDtoList: " + stylelistDtoList.get(0).getId());

        return "layout/StylelistPage";
    }

    // 상품 페이지
    @GetMapping("/clothesdetails")
    public String ClothesDetails(Model model, @RequestParam long id) {

        StylelistDto stylelistDto = userService.getClothesInfoDto(id);
        model.addAttribute("stylelistDto", stylelistDto);

        String stylelistId = userService.getStylelistIdDto(id);
        List<UploadInfo> stylelistDtoList = userService.getClothesListInfoDto(stylelistId);
        model.addAttribute("stylelistDtoList", stylelistDtoList);

        return "layout/ClothesDetails";
    }

    @GetMapping("/member")
    public String memeberForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        UserInfoDto godokList = userService.getUserInfoDto(id, principalDetails.getUserInfo().getUserID());
//        model.addAttribute("godokList", godokList);

        List<UploadInfo> clothesList = userService.getClothesListInfoDto(principalDetails.getUserInfo().getUserID());
        model.addAttribute("clothesList", clothesList);
        System.out.println("stylelistDtoList: " + clothesList.get(0).getId());
        System.out.println("stylelistDtoList: " + clothesList.get(0).getProduct_name());
        System.out.println("stylelistDtoList: " + clothesList.get(0).getProduct_price());


        return "admin/Member";
    }
}
