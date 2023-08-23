package com.example.demo.logIn.service;

import com.example.demo.logIn.dto.NaverOAuthToken;
import com.example.demo.logIn.service.response.NaverOauthAccountInfoResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

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


//    public String getAccessToken(String code) {
//        final String REQUEST_NAVER_ACCESS_TOKEN_URL =
//                //"https://github.com/login/oauth/access_token";
//                "https://nid.naver.com/oauth2.0/token";
//        //final String CLIENT_ID = propertyUtil.getProperty("client_id");
//        final String CLIENT_ID = "U7Yh4r05YNQObUFWynhN";
//        //final String CLIENT_SECRETS = propertyUtil.getProperty("client_secrets");
//        final String CLIENT_SECRETS = "sTfNNHXkCC";
//
//        return restTemplate.postForObject(
//                REQUEST_NAVER_ACCESS_TOKEN_URL,
//                new NaverOauthTokenRequest(CLIENT_ID, CLIENT_SECRETS, code),
//                NaverOauthAccessTokenResponse.class).getAccessToken();
//    }
    @Override
    public NaverOAuthToken generateAccessToken(String code){
        // HTTP Header 생성
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "U7Yh4r05YNQObUFWynhN");
        //body.add("redirect_uri", "http://localhost:8080/authentication/naver/login");
        body.add("code", code);
        body.add("client_secret", "sTfNNHXkCC");
        body.add("state", "1234");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body, headers);
        ResponseEntity<NaverOAuthToken> response
                = restTemplate.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.POST, tokenRequest, NaverOAuthToken.class);

        System.out.println("response" + response);
        //System.out.println(response);
        System.out.println(response.getBody().getAccess_token());


        System.out.println("사용자 정보가져오기");
        //HttpHeaders headers2 = new HttpHeaders();
        headers.add("Authorization", "Bearer " + response.getBody().getAccess_token());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);
        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<String> response2 = restTemplate2.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.POST, request, String.class);
        System.out.println("response2 = " + response2);


        // HTTP 응답 받아오기
        String responseBody = response2.getBody();
        System.out.println("responseBody = " + responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        //JsonNode jsonNode = objectMapper.readTree(responseBody);
        Map<String, Object> jsonMap;
        jsonMap = objectMapper.readValue(responseBody, Map.class);



        return response.getBody();
    }

    @Override
    public String getNaverUserInfo(String code) {
        // accessToken 생성
        String accessToken = generateAccessToken(code).toString();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> naverUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response2 = rt.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                naverUserInfoRequest,
                String.class
        );

        System.out.println("response2" + response2);

        return response2.getBody();

    }

    @Override
    public NaverOauthAccountInfoResponse getAccountInfo(String accessToken) {
        final String REQUEST_GITHUB_USER_API_URL =
                "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        //이렇게 생성된 HttpEntity 객체는 요청에 헤더만 포함하며 본문은 가지지 않습니다.
        HttpEntity<Void> request = new HttpEntity<>(headers);

        NaverOauthAccountInfoResponse response = restTemplate.exchange(
                REQUEST_GITHUB_USER_API_URL,
                HttpMethod.GET,
                request,
                NaverOauthAccountInfoResponse.class).getBody();

        log.info("result: " + response);
        return response;
    }
}
