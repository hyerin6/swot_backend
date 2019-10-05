package kr.devdogs.swot.classroom.service;

import kr.devdogs.swot.classroom.dto.Classroom;
import kr.devdogs.swot.classroom.mapper.ClassroomMapper;
import kr.devdogs.swot.manager.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService{

    @Autowired ClassroomMapper classroomMapper;

    @Override
    public Classroom create(String managerId, Classroom classroom){
        int result = classroomMapper.findByManagerId(managerId);
        if(result == 1){
            classroom.setUid(UUID.randomUUID().toString());
            classroomMapper.create(classroom);
            Classroom currentClass = classroomMapper.findByUid(classroom.getUid());
            return currentClass;
        } else {
            return null;
        }
    }

}
