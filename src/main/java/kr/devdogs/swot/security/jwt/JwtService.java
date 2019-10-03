package kr.devdogs.swot.security.jwt;

public interface JwtService {
    public String accessToken(String subject);
    public String refreshToken(String subject);
    public Result verifyToken(String token);
    public String decode(String token);

    enum Result {
        SUCCESS, EXPIRED, INVALID
    }
}