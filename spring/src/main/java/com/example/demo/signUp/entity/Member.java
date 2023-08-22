package com.example.demo.signUp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String userPw;

    private String userAddress;

    private String userPhone;

    public Member(String userId, String userPw, String userAddress, String userPhone) {
        this.userId = userId;
        this.userPw = userPw;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
    }
}
