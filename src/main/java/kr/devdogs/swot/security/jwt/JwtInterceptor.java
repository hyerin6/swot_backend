package kr.devdogs.swot.security.jwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LogManager.getLogger(JwtInterceptor.class);

    JwtServiceImpl JWT = new JwtServiceImpl();

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        JSONObject JSON = new JSONObject();

        if(req.getHeader("Authorization") == null){
            res.setContentType("application/json");
            JSON.put("statusCode", HttpStatus.BAD_REQUEST);
            JSON.put("statusMsg", "Empty Token");
            return false;
        }

        String[] authHeader = req.getHeader("Authorization").split(" ");
        String token = authHeader[1];
        // 테스트용
        boolean code = JWT.verifyToken(token);

        // 토큰이 만료되었거나 형식이 잘못된 경우
        if (code == false) {
            res.setContentType("application/json");
            JSON.put("statusCode", HttpStatus.UNAUTHORIZED);
            JSON.put("statusMsg", "사용할 수 없는 토큰입니다.");
            res.setCharacterEncoding("UTF-8");
            LOG.warn("사용할 수 없는 토큰입니다.");
            try {
                res.getWriter().write(JSON.toJSONString());
            } catch (IOException e) {
                LOG.warn("Client 연결 끊김", e);
            }
            return false;
        }

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(JWT.decode(token)); // token을 복호화한다. obj = USER.email
        } catch (ParseException e) {
            LOG.warn("파싱 실패", e);
        }
        JSONObject jsonObj = (JSONObject) obj;
        System.out.println(jsonObj);
        req.setAttribute("session", jsonObj);
        return true;
    }
}