package kr.devdogs.swot.user.service.cert;

import kr.devdogs.swot.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userCertServiceImpl")
public class UserCertServiceImpl implements UserCertService{
    @Autowired private UserMapper userMapper;

    // 회원가입 인증
    @Override
    public boolean emailCert(String certToken){
        int result = userMapper.emailCert(certToken);
        if(result == 1){

            return true;
        }
        else return false;
    }

    // 비밀번호 변경 인증
    @Override
    public boolean emailCert2(String certToken){
        int result = userMapper.passwordUpdate(certToken);
        if(result == 1){

            return true;
        }
        else return false;
    }

}
