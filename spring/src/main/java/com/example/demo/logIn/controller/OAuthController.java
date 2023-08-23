package com.example.demo.logIn.controller;

import com.example.demo.logIn.dto.NaverOAuthToken;
import com.example.demo.logIn.service.UserService;
import com.example.demo.logIn.service.response.NaverOauthAccountInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class OAuthController {
    final private UserService userService;
    @GetMapping("/naver/login")
    public String requestNaverAuthorizeCode () {
        log.info("requestGithubAuthorizeCode()");
        return userService.getAuthorizeCode();
    }
    @GetMapping("/naver/oauth-code")
    public NaverOAuthToken getNaverUserInfo(@RequestParam String code) {
        log.info("naverCallback()");
        log.info(code);
        return userService.generateAccessToken(code);
    }
}
