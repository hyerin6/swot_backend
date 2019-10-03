package kr.devdogs.swot.user.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class User {
    String uid;
    String email;
    String password;
    String name;
    String statusMsg; // 상태 메시지
    String major_no; // 학번
    String tempPassword;
    String certToken;
    char state;
    // D - 탈퇴
    // T - 이메일 인증 완료
    // C - 회원가입 완료, 읽기 권한만 있음
    Date createdDate;
    Date updatedDate;
}
