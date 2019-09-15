package kr.devdogs.swot.user.mapper;

import kr.devdogs.swot.user.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 회원가입
    @Insert("INSERT INTO USER(uid, name, email, password, phone, certToken, state, startedDate, updatedDate) " +
            "VALUES(#{uid}, #{name}, #{email}, #{password}, #{phone}, #{certToken}, 'C', now(), now())")
    public int signUp(User user);

    // 로그인 - select
    @Select("SELECT * FROM USER where email = #{email}")
    public User userSignIn(User user);

    // 로그인 - update refresh token 저장
    @Update("UPDATE USER SET refreshToken = #{refreshToken} WHERE email = #{email}")
    public int refreshTokenUpdate(User user);

    // 이메일 중복 체크
    @Select("SELECT email FROM USER WHERE email = #{email}")
    public String emailDuplicate(User user);

}
