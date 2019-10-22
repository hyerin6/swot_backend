package kr.devdogs.swot.user.service.auth;

import kr.devdogs.swot.user.dto.User;
import org.springframework.stereotype.Component;

@Component
public interface UserAuthService {
    // 회원가입
    public boolean userSignUp(User user);

    // 로그인
    public User userSignIn(User user);

    // 이메일 중복 검사, 중복이면 return true
    public boolean isEmailDuplicate(User user);

    // 비밀번호 변경 시, 새로 입력받은 password 저장
    public boolean modifyPw(User user);

}
