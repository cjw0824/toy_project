package com.example.demo.logIn.service;

public interface UserService {
    String getAuthorizeCode();

    String getAccessToken(String code);
}
