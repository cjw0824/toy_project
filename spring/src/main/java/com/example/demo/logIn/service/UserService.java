package com.example.demo.logIn.service;

import com.example.demo.logIn.dto.NaverOAuthToken;
import com.example.demo.logIn.service.response.NaverOauthAccountInfoResponse;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

public interface UserService {
    String getAuthorizeCode();

//    String getAccessToken(String code);

    NaverOAuthToken generateAccessToken(String code);

    String getNaverUserInfo(String code);

    NaverOauthAccountInfoResponse getAccountInfo(String accessToken);
}
