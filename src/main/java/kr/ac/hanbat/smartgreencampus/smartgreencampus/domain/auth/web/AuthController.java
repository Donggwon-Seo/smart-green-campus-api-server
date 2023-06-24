package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.application.AuthService;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web.dto.LoginRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web.dto.LoginResponse;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApi;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApiFailWithAuth;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApiFailWithoutAuth;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.jwt.AuthorizationExtractor;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "인증")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthorizationExtractor authExtractor;
    private final JwtTokenProvider jwtTokenProvider;

//    @SwaggerApi(summary = "회원 가입", implementation = SignupResponse.class)
//    @SwaggerApiFailWithoutAuth
//    @PostMapping("/api/auth/signup")
//    public ResponseEntity<SignupResponse> signupMember(@RequestBody @Valid final SignupRequest request) {
//
//        if (!request.password1().equals(request.password2())) {
//            throw new IllegalValueException("두 비밀번호 값이 일치하지 않습니다.");
//        }
//        return ResponseEntity.ok(new SignupResponse(authService.signUpMember(request)));
//    }


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
    public ResponseEntity<?> logoutMember(HttpServletRequest request) {
        final String token = authExtractor.extract(request, "Bearer");

        try {
            jwtTokenProvider.addBlackList(token);
            log.info(jwtTokenProvider.getSubject(token) + "님이 로그아웃 하셨습니다.");

            return ResponseEntity.ok("logout success!");
        } catch (IllegalAccessException exception) {
            /* do nothing */
            return ResponseEntity.badRequest().build();
        }
    }
}
