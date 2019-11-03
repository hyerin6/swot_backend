package kr.devdogs.swot.classroom.service;

import kr.devdogs.swot.classroom.dto.Classroom;
import kr.devdogs.swot.classroom.mapper.ClassroomMapper;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService{

    @Autowired ClassroomMapper classroomMapper;
    @Autowired UserMapper userMapper;

    // 강의실 생성
    @Override
    public int create(Classroom classroom){
        classroomMapper.create(classroom);
        return classroom.getId();
    }

    // 강의실 id로 select
    @Override
    public Classroom findById(int id){
        return classroomMapper.findById(id);
    }

    @Override
    public List<Classroom> readAll(){
        return classroomMapper.readAll();
    }

    // 강의실 정보 수정
    @Override
    public Classroom modify(Classroom classroom){
        classroomMapper.modify(classroom);
        return classroomMapper.findById(classroom.getId());
    }

    // 강의실 삭제
    @Override
    public boolean delete(int id){
        int deletedLine = classroomMapper.delete(id);
        if(deletedLine == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean auth(int managerId){
        User manager = userMapper.findByUserId(managerId);
        if(manager.getState() == 'M'){
            return true;
        }
        return false;
    }

}
