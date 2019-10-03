package kr.devdogs.swot.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String token = req.getHeader(HEADER_AUTH);

        if(token != null && jwtService.verifyToken(token) == JwtService.Result.SUCCESS){
            req.setAttribute("session", jwtService.decode(token));
            return true;
        } else {
            throw new UnauthorizedException();
        }
    }
}
