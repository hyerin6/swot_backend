package kr.devdogs.swot.reservation.service;

import kr.devdogs.swot.reservation.dto.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    public int create(Reservation reservation);
    public int delete(int id);
    public List<Reservation> findByUserId(int userId);
    public List<Reservation> findByRoomId(int roomId);
    public List<Reservation> readAll();
    public Reservation findById(int id);
    public int accept(int managerId, int id);
    public int decline(int managerId, int id);
    public boolean auth(int managerId);
}
