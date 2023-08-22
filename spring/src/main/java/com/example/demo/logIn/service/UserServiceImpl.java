package com.example.demo.logIn.service;

import com.example.demo.logIn.service.request.NaverOauthTokenRequest;
import com.example.demo.logIn.service.response.NaverOauthAccessTokenResponse;
import com.example.demo.logIn.service.response.NaverOauthAccountInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final private RestTemplate restTemplate;
    //inal private PropertyUtil propertyUtil;
    @Override
    public String getAuthorizeCode() {
        final String URL = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=U7Yh4r05YNQObUFWynhN&redirect_uri=http://localhost:8080/authentication/naver/login&state=1234";
        return URL;
    }

    @Override
    public String getAccessToken(String code) {
        final String REQUEST_GITHUB_ACCESS_TOKEN_URL =
                //"https://github.com/login/oauth/access_token";
                "https://nid.naver.com/oauth2.0/token";
        //final String CLIENT_ID = propertyUtil.getProperty("client_id");
        final String CLIENT_ID = "U7Yh4r05YNQObUFWynhN";
        //final String CLIENT_SECRETS = propertyUtil.getProperty("client_secrets");
        final String CLIENT_SECRETS = "sTfNNHXkCC";

        return restTemplate.postForObject(
                REQUEST_GITHUB_ACCESS_TOKEN_URL,
                new NaverOauthTokenRequest(CLIENT_ID, CLIENT_SECRETS, code),
                NaverOauthAccessTokenResponse.class).getAccessToken();
    }
}
