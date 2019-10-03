package kr.devdogs.swot.reservation.mapper;

import kr.devdogs.swot.reservation.dto.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    public List<Reservation> findByUserId(String userId);
}
