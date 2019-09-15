package kr.devdogs.swot.user.service;

import kr.devdogs.swot.security.jwt.JwtServiceImpl;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.mapper.UserMapper;
import kr.devdogs.swot.user.service.mail.MailService;
import kr.devdogs.swot.util.SHA256Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService{

    @Autowired private UserMapper userMapper;
    @Autowired private SHA256Util sha256Util;
    @Autowired private JwtServiceImpl jwtServiceImpl;
    @Autowired private MailService mailService;

    private static final Logger LOG = LogManager.getLogger(UserAuthServiceImpl.class);

    // 회원가입
    @Override
    public boolean userSignup(User user){
        String pw = user.getPassword();
        String email = user.getEmail();
        try {
            pw = sha256Util.getEncrypt(pw, sha256Util.generateSalt());
        } catch(Exception e) {
            LOG.error("password 암호화 실패", e);
            return false;
        }
        user.setPassword(pw);

        // 이메일 전송 후, token은 디비에 저장
        String token = mailService.send(email);
        user.setCertToken(token);

        user.setUid(UUID.randomUUID().toString());
        int insertedLine = userMapper.signUp(user);
        if(insertedLine == 1) return true;
        else {
            LOG.error("USER Signup Fail : " + user.toString());
            return false;
        }
    }

    // 로그인
    @Override
    public User userSignin(User user){
        String pw = user.getPassword();
        try {
            pw = sha256Util.getEncrypt(pw, sha256Util.generateSalt());
        } catch(Exception e) {
            LOG.error("Password Enctypt Fail - Password : " + pw);
        }
        user.setPassword(pw);

        // refresh token은 USER 디비에 저장한다.
        user.setRefreshToken(jwtServiceImpl.refreshToken(user.getEmail()));
        userMapper.refreshTokenUpdate(user);

        User currentUser = userMapper.userSignIn(user);
        return currentUser;
    }

    // 이메일 중복 검사, 중복이면 return true
    @Override
    public boolean isEmailDuplicate(User user){
        String selectString = userMapper.emailDuplicate(user);

        if(selectString == null)
            return false;
        else
            return true;
    }

}
