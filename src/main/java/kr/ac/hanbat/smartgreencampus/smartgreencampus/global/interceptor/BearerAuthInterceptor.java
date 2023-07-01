package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.token.NotExistTokenException;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.token.NotValidTokenException;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.jwt.AuthorizationExtractor;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BearerAuthInterceptor implements HandlerInterceptor {
    private final AuthorizationExtractor authExtractor;
    private final JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    public BearerAuthInterceptor(
            final AuthorizationExtractor authExtractor,
            final JwtTokenProvider jwtTokenProvider) {
        this.authExtractor = authExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler) {

        final String token = authExtractor.extract(request, "Bearer");

        if (!StringUtils.hasText(token)) {
            throw new NotExistTokenException();
        }
        if (!jwtTokenProvider.validateToken(token)) {
            throw new NotValidTokenException();
        }

        //토큰을 디코딩
        final String name = jwtTokenProvider.getSubject(token);

        //디코딩한 값으로 세팅
        request.setAttribute("name", name);
        return true;
    }
}