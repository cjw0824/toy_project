package com.example.demo.logIn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String id;
    private String nickname;
    private String profile_image;
    private String age;
    private String gender;
    private String mobile;
    private String name;

    public User(String nickname, String profile_image, String age, String gender, String mobile, String name, String id) {
        this.nickname = nickname;
        this.profile_image = profile_image;
        this.age = age;
        this.gender = gender;
        this.mobile = mobile;
        this.name = name;
        this.id = id;
    }
}
