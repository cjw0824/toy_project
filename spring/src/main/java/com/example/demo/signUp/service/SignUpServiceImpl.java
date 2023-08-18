package com.example.demo.signUp.service;

import com.example.demo.signUp.entity.Member;
import com.example.demo.signUp.form.RequestSigUpForm;
import com.example.demo.signUp.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{

    final private SignUpRepository signUpRepository;
//    final private SignUpService signUpService;
    @Override
    public Boolean signUp(Member member) {
        // 해당 아이디가 중복되는지 확인한다
        // 존재하면 그냥 리턴으로 나간다.
        // 존재하지 않으면 등록 리턴으로 나간다.

        Optional<Member> maybeMember = signUpRepository.findByUserId(member.getUserId());
        log.info(member.getUserId());

        if(maybeMember.isPresent()) {
            log.info("존재하는id");
            return false;
        } else {
            log.info("가입가능한id");

            signUpRepository.save(member);
            return true;
        }

    }
}
