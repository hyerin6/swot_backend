package kr.devdogs.swot.user.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class User {
    int id;
    String email;
    String pw;
    String modifyPw;
    String token;
    String name;
    String statusMsg; // 상태메시지
    String studentId; // 학번
    String phone;
    char state;
    // D - 탈퇴
    // T - 이메일 인증 완료
    // C - 회원가입 완료, 이메일 미인증 로그인 안됨
    // M - ADMIN 관리자
    Date createdDate;
    Date updatedDate;
}
