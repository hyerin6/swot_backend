package kr.devdogs.swot.classroom.mapper;

import kr.devdogs.swot.classroom.dto.Classroom;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ClassroomMapper {
    public void create(Classroom classroom);

    public Classroom findById(int id);

    public List<Classroom> readAll();

    public int modify(Classroom classroom);

    public int delete(int id);
}
