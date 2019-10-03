package kr.devdogs.swot.user.service.cert;

public interface UserCertService {

    // 회원가입 이메일 인증
    public boolean emailCert(String certToken);

    // 비밀번호 변경 이메일 인증
    public boolean emailCert2(String certToken);
}
