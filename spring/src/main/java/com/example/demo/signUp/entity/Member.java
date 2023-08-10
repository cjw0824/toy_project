package com.example.demo.signUp.entity;

import com.example.demo.signUp.form.RequestSigUpForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
//@RequiredArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userPw;
    private String tel;

    public Member(String userId, String userPw, String tel) {
        this.userId = userId;
        this.userPw = userPw;
        this.tel = tel;
    }

    //    Member toMember(RequestSigUpForm requestSigUpForm){
//
//        Member member = new Member();
//        member.setUserId(requestSigUpForm.getUserId());
//        member.setUserPw(requestSigUpForm.getUserPw());
//        member.setTel(requestSigUpForm.getTel());
//
//        return member;
//    }
}
