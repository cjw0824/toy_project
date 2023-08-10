package com.example.demo.signUp.repository;

import com.example.demo.signUp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignUpRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
    
}
