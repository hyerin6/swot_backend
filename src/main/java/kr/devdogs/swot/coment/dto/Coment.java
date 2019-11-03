package kr.devdogs.swot.coment.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Data
@Repository
public class Coment {
    int id;
    int boardId;
    int userId;
    String bodyText;
    char state;
    Date createdDate;
    Date updatedDate;
}
