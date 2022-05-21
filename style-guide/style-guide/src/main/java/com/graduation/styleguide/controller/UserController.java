package com.graduation.styleguide.controller;

import com.graduation.styleguide.config.auth.PrincipalDetails;
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
    @GetMapping("/profile")
    public String profile(Model model, @RequestParam long code, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserInfoDto userInfoDto = userService.getUserInfoDto(code, principalDetails.getUserInfo().getCode());
        model.addAttribute("userInfoDto", userInfoDto);
        return "profile";
    }

    //사용자 정보 업데이트
    @PostMapping("/user/update")
    public String updateUser(@Valid UserUpdateDto userUpdateDto, BindingResult bindingResult, @RequestParam("profileImgUrl") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.update(userUpdateDto, multipartFile, principalDetails);
        redirectAttributes.addAttribute("code", principalDetails.getUserInfo().getCode());
        return "redirect:/profile";
    }
}
