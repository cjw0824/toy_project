package com.example.demo.signUp.controller;

import com.example.demo.signUp.form.RequestSigUpForm;
import com.example.demo.signUp.service.SignUpService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class SignUpController {

    final private SignUpService signUpService;
    @PostMapping("/sign-up")
    public Boolean register (@RequestBody RequestSigUpForm requestSigUpForm) {
        log.info("register()");
        log.info(requestSigUpForm.toString());

        return signUpService.signUp(requestSigUpForm.toMember());
    }
}
