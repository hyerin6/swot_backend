package kr.devdogs.swot.classroom.service;

import kr.devdogs.swot.classroom.dto.Classroom;

public interface ClassroomService {
    public Classroom create(String managerId, Classroom classroom);
}
