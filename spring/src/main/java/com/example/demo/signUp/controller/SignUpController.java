package com.example.demo.signUp.controller;

import com.example.demo.signUp.controller.form.SignUpRequestForm;
import com.example.demo.signUp.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/sign-up")
    public boolean signUp(@RequestBody SignUpRequestForm requestForm) {
        log.info("requestForm()", requestForm);

        return signUpService.register(requestForm);
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "");
        return "pages/login/loginPage";
    }

//    @GetMapping("/kakao")
//    public ResponseEntity<ApiResponse<Object>> kakaoLogin(@RequestParam String code) {
//        String answer = "";
//        log.debug("[+| Kakao Login AccessToken :: " + code);
//        ApiResponse<Object> ar = ApiResponse
//                .builder()
//                .result(answer)
//                .resultCode(SUCCESS_CODE)
//                .resultMsg(SUCCESS_MSG).build();
//        return new ResponseEntity<>(ar, HttpStatus.OK);
//    }
}
