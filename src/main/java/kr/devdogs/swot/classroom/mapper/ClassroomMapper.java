package kr.devdogs.swot.classroom.mapper;

import kr.devdogs.swot.classroom.dto.Classroom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassroomMapper {

    @Select("SELECT * FROM manager WHERE id = #{managerId}")
    public int findByManagerId(String managerId);

    @Insert("INSERT INTO classroom "+
            "VALUES(#{uid}, #{group}, #{group_no}, #{room_no}, 'T', #{total}, now(), now())")
    public int create(Classroom classroom);

    @Select("SELECT * FROM classroom WHERE uid = #{uid}")
    public Classroom findByUid(String uid);
}
