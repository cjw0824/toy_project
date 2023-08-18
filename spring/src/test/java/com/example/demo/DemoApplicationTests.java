package com.example.demo;

import com.example.demo.signUp.entity.Member;
import com.example.demo.signUp.form.RequestSigUpForm;
import com.example.demo.signUp.repository.SignUpRepository;
import com.example.demo.signUp.service.SignUpServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

	@Mock
//	가짜 객체(가짜 mock 객체)를 만들어 반환해주는 어노테이션
	private SignUpRepository mockSignUpRepository;
// 	@Mock 또는 @Spy로 생성된 가짜 객체를 자동으로 주입시켜주는 어노테이션
	@InjectMocks
	private SignUpServiceImpl mockSignUpServiceImpl;
	// 각 테스트 메서드 실행 전에
	// MockitoAnnotations.initMocks(this)를 호출하여 Mock 객체를 초기화한다.
	@BeforeEach
	public void setup () throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("회원가입 성공 테스트 - 새로운 회원")
	void signUpSuccessTest() {
		//실제 요청될 데이터
		final RequestSigUpForm requestSigUpForm = new RequestSigUpForm("newID", "newPW", "newTel");
		final Member member = requestSigUpForm.toMember();

		//가상의 예측되는 결과
		//when 함수 : "어떤 동작을 할 때~"
		// .thenReturn, .thenThrow, .thenAnswer : "어떤 것을 한다"
		when(mockSignUpRepository.findByUserId(member.getUserId()))
				.thenReturn(Optional.empty()); // 이미 존재하지 않는 회원

		when(mockSignUpRepository.save(member))
				.thenReturn(new Member("newID", "newPW", "newTel")); // 저장 후 반환

		//실제 구동테스트 => signUp() 메서드를 테스트한다.
		final SignUpServiceImpl sut = new SignUpServiceImpl(mockSignUpRepository);
		Boolean actual = sut.signUp(member);
		assertEquals(actual, true); // 회원가입 성공
	}


	@Test
	@DisplayName("회원가입 실패 테스트 - 이미 존재하는 회원")
	void signUpFailureTest() {
		//실제 요청될 데이터
		final RequestSigUpForm requestSigUpForm = new RequestSigUpForm("existingID", "existingPW", "existingTel");
		final Member member = requestSigUpForm.toMember();

		//가상의 예측되는 결과
		when(mockSignUpRepository.findByUserId(member.getUserId()))
				.thenReturn(Optional.of(new Member("existingID", "existingPW", "existingTel")));

		//실제 구동테스트 => signUp() 메서드를 테스트한다.
		final SignUpServiceImpl sut = new SignUpServiceImpl(mockSignUpRepository);
		Boolean actual = sut.signUp(member);
		assertEquals(actual, false); // 회원가입 실패
	}


}
