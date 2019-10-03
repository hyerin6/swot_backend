package kr.devdogs.swot.user.mapper;

import kr.devdogs.swot.user.dto.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    // 회원가입
    @Insert("INSERT INTO user(uid, name, email, password, statusMsg, major_no, certToken, state, createdDate, updatedDate) " +
            "VALUES(#{uid}, #{name}, #{email}, #{password}, #{statusMsg}, #{major_no}, #{certToken}, 'C', now(), now())")
    public int signUp(User user);

    // 로그인 - select
    @Select("SELECT * FROM user where email = #{email} and password = #{password} and state = 'T'")
    public User userSignIn(User user);

    // 이메일 인증 완료
    @Update("UPDATE user SET state = 'T' WHERE certToken = #{certToken}")
    public int emailCert(String certToken);

    // 이메일 중복 체크
    @Select("SELECT email FROM user WHERE email = #{email}")
    public String emailDuplicate(User user);

    // 비밀번호 변경을 위한 tempPassword 저장 및 state(미인증) 변경
    @Update("UPDATE user SET tempPassword = #{tempPassword}, state = 'C', certToken = #{certToken}, password = NULL, updatedDate = now() " +
            "WHERE email = #{email}")
    public int tempPasswordUpdate(User user);

    // 비밀번호 변경 및 이메일 인증 완료
    @Update("UPDATE user SET state = 'T', password = tempPassword, updatedDate = now() WHERE certToken = #{certToken}")
    public int passwordUpdate(String certToken);

    // 특정 유저 정보 조회
    @Select("SELECT * from user WHERE email = #{email}")
    public User getUser(String email);

    // 회원 탈퇴
    @Update("UPDATE user SET state = 'D', updatedDate = now() WHERE email = #{email}")
    public int withdraw(String email);

    // 유저 정보 업데이트 - 이름, 상태메시지, 학번만 변경할 수 있다.
    @Update("UPDATE user SET name = #{name}, statusMsg = #{statusMsg}, major_no = #{major_no}, updatedDate = now() " +
            "WHERE email = #{email}")
    public int userInfoUpdate(User user);

}
