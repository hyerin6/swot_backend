package kr.devdogs.swot.board.dto;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Data
@Repository
public class Board {
    int id;
    int code;
    int userId;
    String title;
    String body;
    char state;
    Date createdDate;
    Date updatedDate;

    // 스터디 예약 정보 필드가 필요함
    int roomId; // 스터디 하고 싶은 장소
    String phone; // 사용자 전화번호
    int total; // 원하는 인원수
    String startTime; // 시작 시간
    String endTime; // 종료 시간
    String meetingDate; // 원하는 날짜

}
