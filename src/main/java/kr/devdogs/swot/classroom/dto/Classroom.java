package kr.devdogs.swot.classroom.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class Classroom {
    int id;
    String groupName;
    int groupNo;
    String roomNo;
    char state;
    int total;
    Date createdDate;
    Date updatedDate;
}
