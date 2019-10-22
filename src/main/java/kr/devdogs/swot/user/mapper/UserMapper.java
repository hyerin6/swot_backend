package kr.devdogs.swot.user.mapper;

import kr.devdogs.swot.user.dto.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    // 회원가입
    @Insert("INSERT INTO user(email, pw, name, studentId, phone, statusMsg, token, state, createdDate, updatedDate) " +
            "VALUES(#{email}, #{pw}, #{name}, #{studentId}, #{phone}, #{statusMsg}, #{token}, 'C', now(), now())")
    public int signUp(User user);

    // 로그인을 위한 user 정보 조회
    @Select("SELECT * FROM user " +
            "where email = #{email} " +
            "AND pw = #{pw} " +
            "AND NOT state='D'")
    public User userSignIn(User user);

    // 이메일 인증 완료, state 변경
    @Update("UPDATE user SET state = 'T' WHERE token = #{token}")
    public int emailCert(String certToken);

    // 이메일 중복 확인
    @Select("SELECT email FROM user WHERE email = #{email} AND NOT state='D'")
    public String emailDuplicate(User user);

    // 비밀번호 변경을 위한 user 정보 update
    @Update("UPDATE user SET modifyPw = #{modifyPw}, state = 'C', token = #{token} " +
            "WHERE email = #{email}")
    public int modifyPwUpdate(User user);

    // 비밀번호 변경 및 이메일 인증 완료
    @Update("UPDATE user SET state = 'T', pw = modifyPw " +
            "WHERE token = #{token}")
    public int modifyPw(String token);

    // 특정 유저 정보 조회
    @Select("SELECT * from user WHERE id = #{id}")
    public User findByUserId(int id);

    // 회원 탈퇴
    @Update("UPDATE user SET state = 'D' WHERE id = #{id}")
    public int withdraw(int id);

    // 유저 정보 업데이트 - 이름, 상태메시지, 학번만 변경
    @Update("UPDATE user SET name = #{name}, statusMsg = #{statusMsg}, studentId = #{studentId} " +
            "WHERE id = #{id}")
    public int modifyUserInfo(User user);

}
