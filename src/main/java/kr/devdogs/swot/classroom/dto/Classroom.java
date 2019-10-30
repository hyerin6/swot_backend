package kr.devdogs.swot.classroom.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class Classroom {
    int id;
    int groupNo; // 예) 6
    String roomNo; // 예) m402
    String roomName; // 예) 미가엘관
    char state;
    int total;
    Date createdDate;
    Date updatedDate;
}
