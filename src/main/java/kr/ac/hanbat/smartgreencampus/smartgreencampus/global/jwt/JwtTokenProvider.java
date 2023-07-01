package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.jwt;

import io.jsonwebtoken.*;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.token.IllegalTokenException;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.token.NotExistTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final String secretKey;

    @Autowired private BlackListRepository blackListRepository;

    public JwtTokenProvider(
            @Value("${security.jwt.token.secret-key}") final String secretKey) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(final String subject) { //email
        Claims claims = Jwts.claims().setSubject(subject);

        Date now = new Date();
        log.info("now: {}", now);


        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        log.info("생성된 JWT: {}", token);
        return token;
    }

    /**
     * 토큰에서 값 추출
     */
    public String getSubject(final String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 유효한 토큰인지 확인
     */
    public boolean validateToken(final String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            /** 로그아웃 요청한 토큰(= 블랙리스트에 포함)일 경우 */
            if (blackListRepository.existsByToken(token)) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public void addBlackList(final String token) throws IllegalAccessException {

        if (blackListRepository.existsByToken(token)) {
            throw new IllegalTokenException();
        }

        if (!validateToken(token)) {
            throw new NotExistTokenException();
        }

        blackListRepository.save(BlackList.builder()
                .token(token)
                .build());
    }
}
