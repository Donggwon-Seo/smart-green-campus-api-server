package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.application.AuthService;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web.dto.SignupRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web.dto.SignupResponse;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web.dto.LoginRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web.dto.LoginResponse;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApi;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApiFailWithAuth;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApiFailWithoutAuth;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.IllegalValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @SwaggerApi(summary = "회원 가입", implementation = SignupResponse.class)
    @SwaggerApiFailWithoutAuth
    @PostMapping("/api/auth/signup")
    public ResponseEntity<SignupResponse> signupMember(@RequestBody @Valid final SignupRequest request) {

        if (!request.password1().equals(request.password2())) {
            throw new IllegalValueException("두 비밀번호 값이 일치하지 않습니다.");
        }
        return ResponseEntity.ok(new SignupResponse(authService.signUpMember(request)));
    }


    @SwaggerApi(summary = "로그인", implementation = ResponseEntity.class)
    @SwaggerApiFailWithoutAuth
    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> loginMember(@RequestBody @Valid final LoginRequest request) {
        final String token = authService.loginMember(request);
        return ResponseEntity.ok(new LoginResponse(token, "bearer"));
    }


    @SwaggerApi(summary = "로그아웃", implementation = ResponseEntity.class)
    @SwaggerApiFailWithAuth
    @PostMapping("/api/auth/logout")
    public ResponseEntity<String> logoutMember(HttpServletRequest request) {

        if (authService.logout(request)) {
            return ResponseEntity.ok("logout success");
        } else {
            return ResponseEntity.badRequest().body("bad request");
        }
    }
}
