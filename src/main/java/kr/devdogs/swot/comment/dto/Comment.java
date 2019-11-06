package kr.devdogs.swot.comment.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Data
@Repository
public class Comment {
    int id;
    int boardId;
    int userId;
    String name; // user name
    String bodyText;
    char state;
    Date createdDate;
    Date updatedDate;
}
