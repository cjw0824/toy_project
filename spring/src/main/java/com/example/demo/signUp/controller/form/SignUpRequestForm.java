package com.example.demo.signUp.controller.form;

import com.example.demo.signUp.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestForm {

     private String userId;
     private String userPw;
     private String userAddress;
     private String userPhone;

    public Member toMember() {
        return Member.builder()
                .userId(userId)
                .userPw(userPw)
                .userAddress(userAddress)
                .userPhone(userPhone)
                .build();
    }
}


