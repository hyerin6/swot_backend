package kr.devdogs.swot.user.service;

import kr.devdogs.swot.user.dto.User;

public interface UserAuthService {
    // 회원가입
    public boolean userSignup(User user);

    // 로그인
    public User userSignin(User user);

    // 이메일 중복 검사, 중복이면 return true
    public boolean isEmailDuplicate(User user);

}
