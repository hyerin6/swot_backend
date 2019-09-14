package kr.devdogs.swot.security.jwt;

public interface JwtService {
    public String accessToken(String subject);
    public String refreshToken(String subject);
    public int verifyToken(String token);
    public String decode(String token);
}