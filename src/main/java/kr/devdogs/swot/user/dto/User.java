package kr.devdogs.swot.user.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class User {
    String uid;
    String name;
    String email;
    String password;
    String phone;
    String tempPassword;
    String certToken;
    String refreshToken;
    char state;
    /* USER state
     */
    Date startedDate;
    Date updatedDate;

}
