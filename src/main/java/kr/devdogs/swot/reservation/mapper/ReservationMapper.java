package kr.devdogs.swot.reservation.mapper;

import kr.devdogs.swot.reservation.dto.Reservation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationMapper {

    // 예약
    public void create(Reservation reservation);

    // findById
    public Reservation findById(int id);

    // 사용자 - 삭제
    public int delete(int id);

    // 특정 사용자 강의실 예약 목록
    public List<Reservation> findByUserId(int userId);

    // 특정 강의실 예약 목록
    public List<Reservation> findByRoomId(int roomId);

    // 전체 예약 목록
    public List<Reservation> readAll();

    // 관리자 - 승인
    public int accept(int id);

    // 관리자 - 거절
    public int decline(int id);
}
