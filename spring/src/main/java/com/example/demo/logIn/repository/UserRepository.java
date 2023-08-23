package com.example.demo.logIn.repository;

import com.example.demo.logIn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
