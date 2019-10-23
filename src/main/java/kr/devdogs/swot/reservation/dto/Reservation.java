package kr.devdogs.swot.reservation.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;

@Data
@Repository
public class Reservation {
    int id;
    int roomId;
    int userId;
    String reason; // 예약 사유
    String phone; // 사용자 전화번호
    int total; // 신청하는 총 인원수
    Time startTime; // 시작 시간
    Time endTime; // 종료 시간
    Date reservationDate; // 날짜
    char state; // 수락 OR 거절
    Date createdDate; // 신청한 날짜와 시간
    Date updatedDate; // 처리 시간

}
