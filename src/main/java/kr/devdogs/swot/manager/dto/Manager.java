package kr.devdogs.swot.manager.dto;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class Manager {
    int uid; // auto_increment
    String id;
    String password;
    char state;
    // T - 모든 권한이 있는 manager
    // D - 모든 권한이 없는 manager (삭제된 관리자)
}

