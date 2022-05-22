package com.graduation.styleguide.controller;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.dto.UploadPostDto;
import com.graduation.styleguide.handler.CustomValidationException;
import com.graduation.styleguide.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLOutput;

@RequiredArgsConstructor
@Controller
public class UploadController {

    private final UploadService uploadService;

    //포스트 업로드 화면으로 이동
    @GetMapping("upload")
    public String upload() {
        System.out.println("get access");
        return "adminpage/upload";
    }

    //포스트 업로드 후 프로필 화면으로 이동
    @PostMapping("upload")
    public String uploadPost(UploadPostDto uploadPostDto, @RequestParam("pic_name") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(multipartFile.isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다");
        }
        uploadService.save(uploadPostDto, multipartFile, principalDetails);
        redirectAttributes.addAttribute("id", principalDetails.getUserInfo().getUserID());
        System.out.println("post access");
        return "redirect:/";
    }

//    //포스트 수정 화면으로 이동
//    @GetMapping("/post/update/{postId}")
//    public String update(@PathVariable long postId, Model model) {
//        PostDto postDto = uploadService.getPostDto(postId);
//        model.addAttribute("postDto", postDto);
//        return "post/update";
//    }
//
//    //포스트 수정 폼
//    @PostMapping("/post/update")
//    public String postUpdate(PostUpdateDto postUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes redirectAttributes) {
//        uploadService.update(postUpdateDto);
//        redirectAttributes.addAttribute("id", principalDetails.getUser().getId());
//        return "redirect:/user/profile";
//    }
//
//    //포스트 삭제 폼
//    @PostMapping("/post/delete")
//    public String delete(@RequestParam("postId") long postId, @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes redirectAttributes) {
//        uploadService.delete(postId);
//        redirectAttributes.addAttribute("id", principalDetails.getUser().getId());
//        return "redirect:/user/profile";
//    }
//
//    //검색 페이지 - 게시글의 태그 눌러서 이동
//    @GetMapping("/post/search")
//    public String search(@RequestParam("tag") String tag, Model model) {
//        model.addAttribute("tag", tag);
//        return "post/search";
//    }
//
//    //검색 폼 입력 후 페이지 이동
//    @PostMapping("/post/searchForm")
//    public String searchForm(String tag, RedirectAttributes redirectAttributes) {
//        redirectAttributes.addAttribute("tag", tag);
//        return "redirect:/post/search";
//    }
//
//    //좋아요한 포스트 출력 페이지로 이동
//    @GetMapping("/post/likes")
//    public String likes() {
//        return "post/likes";
//    }
//
//    //인기 포스트 페이지로 이동
//    @GetMapping("/post/popular")
//    public String popular() {
//        return "post/popular";
//    }

}
