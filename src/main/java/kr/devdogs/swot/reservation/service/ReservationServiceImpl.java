package kr.devdogs.swot.reservation.service;

import kr.devdogs.swot.reservation.dto.Reservation;
import kr.devdogs.swot.reservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService{
    @Autowired ReservationMapper reservationMapper;

    @Override
    public List<Reservation> findByUserId(String userId){
        List<Reservation> reservations = reservationMapper.findByUserId(userId);
        return reservations;
    }
}
