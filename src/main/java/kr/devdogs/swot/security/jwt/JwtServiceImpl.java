package kr.devdogs.swot.security.jwt;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{

    private static final Logger LOG = LogManager.getLogger(JwtServiceImpl.class);
    private static final String SALT = "DBcapSecret";

    // accessToken 발급
    @Override
    public String accessToken(String subject) {
        Date Now = new Date();
        // 유효 기간 14일
        Date expireTime = new Date(Now.getTime() + 1000 * 60 * 60 * 24 * 14);
        String jwt = Jwts.builder()
                .setExpiration(expireTime)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SALT)
                .compact();
        return jwt;
    }

    // refreshToken 발급
    @Override
    public String refreshToken(String subject) {
        Date Now = new Date();
        // 유효 기간 30일
        Date expireTime = new Date(Now.getTime() + 1000 * 60 * 60 * 24 * 30);
        String jwt = Jwts.builder()
                .setExpiration(expireTime)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SALT)
                .compact();
        return jwt;
    }

    // token 확인
    @Override
    public boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(SALT).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            LOG.info("Invalid JWT signature.");
            LOG.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            LOG.info("Invalid JWT token.");
            LOG.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            LOG.info("Expired JWT token.");
            LOG.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            LOG.info("Unsupported JWT token.");
            LOG.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            LOG.info("JWT token compact of handler are invalid.");
            LOG.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    @Override
    public String decode(String token) {
        Claims Claim = Jwts.parser().setSigningKey(SALT).parseClaimsJws(token).getBody();
        return Claim.getSubject();
    }
}
