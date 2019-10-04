package kr.devdogs.swot.reservation.service;

import kr.devdogs.swot.reservation.dto.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    public boolean createReservation(Reservation reservation);
    public boolean deleteReservation(Reservation reservation);
    public List<Reservation> findByUserId(String userId);
}
