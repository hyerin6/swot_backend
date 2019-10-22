package kr.devdogs.swot.user.service.cert;

public interface UserCertService {

    // 이메일 인증
    public boolean emailCert(String token);

    // 회원가입 이메일 인증 성공
    public void successSignUpCert(String token);

    // 비밀번호 변경 이메일 인증 성공
    public void successModifyPwCert(String token);

}
