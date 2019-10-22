package kr.devdogs.swot.user.service.cert;

import kr.devdogs.swot.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userCertServiceImpl")
public class UserCertServiceImpl implements UserCertService{
    @Autowired private UserMapper userMapper;

    // 이메일 인증
    @Override
    public boolean emailCert(String token){
        int result = userMapper.emailCert(token);
        if(result == 1){
            return true;
        } else {
            return false;
        }
    }

    // 회원가입 이메일 인증 성공
    public void successSignUpCert(String token){
        userMapper.emailCert(token);
    }

    // 비밀번호 변경 이메일 인증 성공
    public void successModifyPwCert(String token){
        userMapper.modifyPw(token);
    }

}
