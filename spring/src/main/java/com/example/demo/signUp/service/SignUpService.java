package com.example.demo.signUp.service;

import com.example.demo.signUp.entity.Member;
import com.example.demo.signUp.form.RequestSigUpForm;

public interface SignUpService {
    Boolean signUp(Member member);
}
