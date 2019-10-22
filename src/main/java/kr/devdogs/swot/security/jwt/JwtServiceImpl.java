package kr.devdogs.swot.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

import static kr.devdogs.swot.security.jwt.JwtService.Result.SUCCESS;
import static kr.devdogs.swot.security.jwt.JwtService.Result.INVALID;
import static kr.devdogs.swot.security.jwt.JwtService.Result.EXPIRED;

@Service("jwtService")
@Slf4j
public class JwtServiceImpl implements JwtService{
    private static final String SALT = "swotSecret";

    // access token 생성
    @Override
    public String accessToken(int subject) {
        Date Now = new Date();
        Date expireTime = new Date(Now.getTime() + 1000 * 60 * 60 * 24 * 14);
        String jwt = Jwts.builder()
                .setExpiration(expireTime)
                .setSubject(Integer.toString(subject))
                .signWith(SignatureAlgorithm.HS256, SALT)
                .compact();
        return jwt;
    }

    // refresh token 생성
    @Override
    public String refreshToken(String subject) {
        Date Now = new Date();
        Date expireTime = new Date(Now.getTime() + 1000 * 60 * 60 * 24 * 365 * 5);
        String jwt = Jwts.builder()
                .setExpiration(expireTime)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SALT)
                .compact();
        return jwt;
    }

    // token 검사
    @Override
    public Result verifyToken(String token) {
        try{
            Jwts.parser().setSigningKey(SALT).parseClaimsJws(token).getBody();
            return SUCCESS;
        }catch (ExpiredJwtException e) {
            log.error("토큰 틀렸습니다.", e);
            return EXPIRED;
        } catch (JwtException e) {
            log.error("토큰 틀렸습니다.", e);
            return INVALID;
        }
    }

    // Token 해독 및 객체 생성
    @Override
    public int decode(String token) {
        Claims claim = Jwts.parser().setSigningKey(SALT).parseClaimsJws(token).getBody();
        String subject = claim.getSubject();
        return Integer.parseInt(subject);
    }
}