package kr.devdogs.swot.user.service.auth;

import kr.devdogs.swot.user.dto.User;
import org.springframework.stereotype.Component;

@Component
public interface UserAuthService {
    // 회원가입
    public boolean userSignup(User user);

    // 로그인
    public User userSignin(User user);

    // 이메일 중복 검사, 중복이면 return true
    public boolean isEmailDuplicate(User user);

    // 비밀번호를 잊어버렸을 때, tempPassword 저장
    public boolean tempPasswordUpdate(User user);


}
