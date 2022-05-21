package com.graduation.styleguide.controller.api;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SubscribeController {

    private final SubscribeService subscribeService;

    @PostMapping("/subscribe/{stylelistId}")
    public ResponseEntity<?> followUser(@PathVariable long stylelistId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.follow(principalDetails.getUserInfo().getCode(), stylelistId);
        return new ResponseEntity<>("팔로우 성공", HttpStatus.OK);
    }

    @DeleteMapping("/subscribe/{stylelistId}")
    public ResponseEntity<?> unFollowUser(@PathVariable long stylelistId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.unFollow(principalDetails.getUserInfo().getCode(), stylelistId);
        return new ResponseEntity<>("팔로우 취소 성공", HttpStatus.OK);
    }

}
