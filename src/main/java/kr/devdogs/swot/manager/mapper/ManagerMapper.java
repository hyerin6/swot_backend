package kr.devdogs.swot.manager.mapper;

import kr.devdogs.swot.manager.dto.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ManagerMapper {

    // 로그인 - select
    @Select("SELECT * FROM manager where id = #{id} and password = #{password} and state = 'T'")
    public Manager managerSignin(Manager manager);

    // info
    @Select("SELECT * from manager WHERE id = #{id}")
    public Manager getMyInfo(String id);


}
