package kr.devdogs.swot.user.service.auth;

import kr.devdogs.swot.security.jwt.JwtServiceImpl;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.mapper.UserMapper;
import kr.devdogs.swot.user.service.mail.MailService;
import kr.devdogs.swot.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService{

    @Autowired private UserMapper userMapper;
    @Autowired private SHA256Util sha256Util;
    @Autowired private JwtServiceImpl jwtServiceImpl;
    @Autowired private MailService mailService;

    // 회원가입
    @Override
    public boolean userSignup(User user){
        String pw = user.getPassword();
        String email = user.getEmail();
        try {
            pw = sha256Util.getEncrypt(pw);
        } catch(Exception e) {
            //LOG.error("password 암호화 실패", e);
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
            //LOG.error("USER Signup Fail : " + user.toString());
            return false;
        }
    }

    // 로그인
    @Override
    public User userSignin(User user){
        String pw = user.getPassword();
        try {
            pw = sha256Util.getEncrypt(pw);
        } catch(Exception e) {
            //LOG.error("Password Enctypt Fail - Password : " + pw);
        }
        // 암호화된 패스워드로 변경
        user.setPassword(pw);

        // user가 있는지 디비에서 조회
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

    // tempPassword DB에 저장
    @Override
    public boolean tempPasswordUpdate(User user){
        // email이랑 tempPassword 포함되어있어야함
        String tempPassword = user.getTempPassword();
        try {
            tempPassword = sha256Util.getEncrypt(tempPassword);
        } catch(Exception e) {
            //LOG.error("password 암호화 실패", e);
            return false;
        }
        user.setTempPassword(tempPassword);

        // 이메일 전송 후, token은 디비에 저장
        String token = mailService.send(user.getEmail());
        user.setCertToken(token);

        // certToken, state, tempPassword 는 새로운 값으로 변경해주고
        // password 는 NULL 값으로 바꿔준다.
        int result = userMapper.tempPasswordUpdate(user);

        if(result == 1) return true;
        else return false;
    }

}
