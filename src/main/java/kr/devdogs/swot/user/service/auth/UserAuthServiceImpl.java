package kr.devdogs.swot.user.service.auth;

import kr.devdogs.swot.security.jwt.JwtServiceImpl;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.mapper.UserMapper;
import kr.devdogs.swot.user.service.mail.MailService;
import kr.devdogs.swot.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService{

    @Autowired private UserMapper userMapper;
    @Autowired private SHA256Util sha256Util;
    @Autowired private JwtServiceImpl jwtServiceImpl;
    @Autowired private MailService mailService;

    // 회원가입
    @Override
    public boolean userSignUp(User user){
        String pw = user.getPw();
        String email = user.getEmail();
        try {
            pw = sha256Util.getEncrypt(pw);
        } catch(Exception e) {
            //LOG.error("password 암호화 실패", e);
            return false;
        }
        user.setPw(pw);

        // 이메일 전송 후, token 은 디비에 저장
        String token = mailService.send(email, 1);
        user.setToken(token);

        int insertedLine = userMapper.signUp(user);
        if(insertedLine == 1){
            return true;
        } else {
            //LOG.error("");
            return false;
        }
    }

    // 로그인
    @Override
    public User userSignIn(User user){
        String pw = user.getPw();
        try {
            pw = sha256Util.getEncrypt(pw);
        } catch(Exception e) {
            //LOG.error("");
        }
        user.setPw(pw);

        User currentUser = userMapper.userSignIn(user);
        return currentUser;
    }

    // 이메일 중복 검사, 중복이면 return true
    @Override
    public boolean isEmailDuplicate(User user){
        String selectString = userMapper.emailDuplicate(user);

        if(selectString == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean modifyPw(User user){
        String modifyPw = user.getModifyPw();
        try {
            modifyPw = sha256Util.getEncrypt(modifyPw);
        } catch(Exception e) {
            //LOG.error("password 암호화 실패", e);
            return false;
        }
        user.setModifyPw(modifyPw);

        // 이메일 전송 후, token 은 디비에 저장
        String token = mailService.send(user.getEmail(), 2);
        user.setToken(token);

        int updatedLine = userMapper.modifyPwUpdate(user);

        if(updatedLine == 1){
            return true;
        } else{
            return false;
        }
    }

}
