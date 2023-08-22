package com.example.demo.logIn.controller;

import com.example.demo.logIn.service.UserService;
import com.example.demo.logIn.service.response.NaverOauthAccountInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/naver/oauth-code")
//    public String getNaverUserInfo(@RequestParam String code) {
//        log.info("getNaverUserInfo(): " + code);
//        return code;
//    }
    @GetMapping("/naver/oauth-code")
    public String getNaverUserInfo(@RequestParam String code) {
        final Long NO_ACCOUNT = -1L;

        log.info("getNaverUserInfo(): " + code);

        String accessToken = userService.getAccessToken(code);
        log.info("accessToken: " + accessToken);

        //NaverOauthAccountInfoResponse oauthAccountInfoResponse =
        //        userService.getAccountInfo(accessToken);

        //String email = oauthAccountInfoResponse.getEmail();
        //Long accountId = accountService.findAccountIdByEmail(email);

        //if (accountId == NO_ACCOUNT) {
        //    log.info("ready to register new account!");
        //    accountId = accountService.signUpWithEmail(email);
        //}

        String userToken = UUID.randomUUID().toString();
       //log.info("accountId: " + accountId + ", userToken: " + userToken);

        //redisService.setKeyAndValue(userToken, accountId);
        //return accessToken;
        return userToken;
    }
}
