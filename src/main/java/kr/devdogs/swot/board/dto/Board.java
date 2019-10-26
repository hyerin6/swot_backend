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
}
