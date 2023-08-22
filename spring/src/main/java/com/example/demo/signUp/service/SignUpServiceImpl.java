package com.example.demo.signUp.service;

import com.example.demo.signUp.controller.form.SignUpRequestForm;
import com.example.demo.signUp.entity.Member;
import com.example.demo.signUp.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{

    private final SignUpRepository signUpRepository;

    @Override
    public boolean register(SignUpRequestForm requestForm) {
        // 1. 해당 아이디가 존재하는 지?
        Optional<Member> maybeMember = signUpRepository.findByUserId(requestForm.getUserId());
        log.info("maybeMember", maybeMember);

        // 1-1 (존재 시) false 혹은 return
        if(maybeMember.isPresent()) {
            log.info("존재하는 아이디 입니다.");
            return false;
        }

        // 1-2 (미존재) -> 등록 ! true 혹은 return
        Member member = requestForm.toMember();
        signUpRepository.save(member);
        return true;
    }
}
