package kr.devdogs.swot.classroom.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class Classroom {
    String uid;
    String group; // 예) 미가엘관
    int group_no; // 예) 12
    String room_no; // 예) m301
    char state; // T:예약가능, D:예약불가능
    int total; // 수용 가능 인원수
    Date createdDate;
    Date updatedDate;
}
