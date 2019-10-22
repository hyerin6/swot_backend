package kr.devdogs.swot.security.jwt;

public interface JwtService {
    public String accessToken(int subject);
    public String refreshToken(String subject);
    public Result verifyToken(String token);
    public int decode(String token);

    enum Result {
        SUCCESS, EXPIRED, INVALID
    }
}