package kr.devdogs.swot.application.dto;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Data
@Repository
public class Application {
    int id;
    int boardId;
    int userId;
    char state;
    String startTime;
    String endTime;
    String applicationDate;
    int total;
    Date createdDate;
    Date updatedDate;
}
