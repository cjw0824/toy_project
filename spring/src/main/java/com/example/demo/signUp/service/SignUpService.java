package com.example.demo.signUp.service;

import com.example.demo.signUp.controller.form.SignUpRequestForm;

public interface SignUpService {
    boolean register(SignUpRequestForm requestForm);
}
