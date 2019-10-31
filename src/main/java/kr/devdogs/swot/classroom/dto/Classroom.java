package kr.devdogs.swot.classroom.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class Classroom {
    int id;
    int groupNo; // 예) 6
    String roomNo; // 예) 6202
    String roomName; // 예) 소프트웨어공학과 실습실 
    char state;
    int total;
    Date createdDate;
    Date updatedDate;
}
