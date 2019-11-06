package kr.devdogs.swot.reservation.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class Reservation {
    int id;
    int roomId;
    int userId;
    String reason; // 사용자의 예약 사유
    String failReason; // 관리자의 거절 사유
    String phone; // 사용자 전화번호
    int total; // 신청하는 총 인원수
    String startTime; // 시작 시간
    String endTime; // 종료 시간
    String reservationDate; // 날짜
    char state; // 수락 OR 거절
    Date createdDate; // 신청한 날짜와 시간
    Date updatedDate; // 처리 시간
    String studentId;
    String studentName;
}
