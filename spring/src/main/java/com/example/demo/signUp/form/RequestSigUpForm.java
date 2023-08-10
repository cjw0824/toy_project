package com.example.demo.signUp.form;

import com.example.demo.signUp.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestSigUpForm {
//    아래 항목은 프론트에서 받아온 값의 항목과 동일해야합니다.
    final private String userId;
    final private String userPw;
    final private String tel;

    public Member toMember(){
            return new Member(userId, userPw, tel);
    }

}
