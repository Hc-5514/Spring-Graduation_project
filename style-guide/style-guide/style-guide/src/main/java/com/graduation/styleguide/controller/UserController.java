package com.graduation.styleguide.controller;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.dto.BusinessInfoDto;
import com.graduation.styleguide.dto.UserInfoDto;
import com.graduation.styleguide.dto.UserUpdateDto;
import com.graduation.styleguide.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    //사용자 프로필 화면으로 이동
    @GetMapping("/testprofile")
    public String profile(Model model, @RequestParam String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserInfoDto userInfoDto = userService.getUserInfoDto(id, principalDetails.getUserInfo().getUserID());
        model.addAttribute("userInfoDto", userInfoDto);
        return "testProfile";
    }

    //사용자 정보 업데이트
    @PostMapping("/user/update")
    public String updateUser(@Valid UserUpdateDto userUpdateDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.update(userUpdateDto, principalDetails);
        redirectAttributes.addAttribute("code", principalDetails.getUserInfo().getCode());
        return "redirect:/profile";
    }

    //사업자 정보 업데이트
    @PostMapping("/business/update")
    public String updateBusiness(@Valid BusinessInfoDto businessInfoDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.updateBusinessInfo(businessInfoDto, principalDetails);
        redirectAttributes.addAttribute("code", principalDetails.getUserInfo().getCode());
        return "redirect:/profile";
    }
}
