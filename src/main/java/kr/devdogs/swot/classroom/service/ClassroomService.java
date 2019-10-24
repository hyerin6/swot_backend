package kr.devdogs.swot.classroom.service;

import kr.devdogs.swot.classroom.dto.Classroom;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClassroomService {
    public int create(Classroom classroom);
    public Classroom findById(int id);
    public List<Classroom> readAll();
    public Classroom modify(Classroom classroom);
    public boolean delete(int id);
    public boolean auth(int managerId);
}
