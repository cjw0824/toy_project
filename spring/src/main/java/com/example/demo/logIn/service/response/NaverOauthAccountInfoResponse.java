package com.example.demo.logIn.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaverOauthAccountInfoResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("profile_image")
    private String profile_image;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birthyear")
    private String birthyear;

    @JsonProperty("mobile")
    private String mobile;

//    @JsonProperty("id")
//    private String id;
//
//    @JsonProperty("login")
//    private String name;
//
//    @JsonProperty("email")
//    private String email;
//
//    @JsonProperty("avatar_url")
//    private String avatarUrl;
//
//    @JsonProperty("html_url")
//    private String githubRepository;
//
//    @JsonProperty("public_repos")
//    private Long publicRepositoryNumber;
}
