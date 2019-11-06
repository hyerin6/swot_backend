package kr.devdogs.swot.application.dto;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Data
@Repository
public class Application { // 스터디 예약
    int id;
    int boardId;
    /*
    userId는 신청한 사용자의 id 이다.
    스터디 모집하는 사용자의 id는
    board(userId) 테이블에서 확인할 수 있다.
    */
    String applicationDate;
    int userId;
    char state;
    Date createdDate;
    Date updatedDate;
}
