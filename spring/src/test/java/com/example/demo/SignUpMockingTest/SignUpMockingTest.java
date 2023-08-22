package com.example.demo.SignUpMockingTest;

import com.example.demo.signUp.controller.form.SignUpRequestForm;
import com.example.demo.signUp.entity.Member;
import com.example.demo.signUp.repository.SignUpRepository;
import com.example.demo.signUp.service.SignUpServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SignUpMockingTest {

    @Mock
    private SignUpRepository mockSignUpRepository;
    @Autowired
    private SignUpRepository signUpRepository;
    @InjectMocks
    private SignUpServiceImpl mockSighUpService;

    // 테스트 실행 전 후에 특정 동작을 수행할 수 있다. (Mock을 초기화시켜주는 중)
    @BeforeEach
    public void setup () throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Mocking: 회원가입 테스트")
    public void 회원가입을_합니다 () {
        // 실제 요청될 데이터
        final SignUpRequestForm requestForm = new SignUpRequestForm(
                "test3", "1234", "강남구", "010-2222-3333"
        );

         final Member member = requestForm.toMember();

//         //가상의 예측되는 결과
////        when(mockSignUpRepository.save(member))
////                .thenReturn(new Member("test", "1234", "강남구", "010-2222-3333"));
//
//        when(mockSignUpRepository.save(member))
//                .thenReturn(null); // 예외를 던지도록 설정하거나
//
//        // 실제 구동 테스트
//        final SignUpServiceImpl sign = new SignUpServiceImpl(mockSignUpRepository);
//        final Boolean isPossible = sign.register(requestForm);
//
//        //assertEquals(isPossible, true);
//        assertTrue(isPossible);

        // 성공적으로 저장되는 경우
        when(mockSignUpRepository.save(member))
                .thenReturn(member);


        // 실제 구동 테스트
        final SignUpServiceImpl sign = new SignUpServiceImpl(mockSignUpRepository);

        // 회원가입 성공 시
        final Boolean isPossibleSuccess = sign.register(requestForm);
        assertTrue(isPossibleSuccess);

    }

}
