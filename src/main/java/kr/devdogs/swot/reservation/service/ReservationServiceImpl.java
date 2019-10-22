package kr.devdogs.swot.reservation.service;

import kr.devdogs.swot.reservation.dto.Reservation;
import kr.devdogs.swot.reservation.mapper.ReservationMapper;
import kr.devdogs.swot.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService{
    @Autowired ReservationMapper reservationMapper;
    @Autowired UserMapper userMapper;

    public int create(Reservation reservation){
        System.out.println(reservation.getDt());
        reservationMapper.create(reservation);
        reservation.getId();
        return reservation.getId();
    }

    public int delete(int id){
        return reservationMapper.delete(id);
    }

    public List<Reservation> findByUserId(int userId){
        return reservationMapper.findByUserId(userId);
    }

    public List<Reservation> findByRoomId(int roomId){
        return reservationMapper.findByRoomId(roomId);
    }

    public List<Reservation> readAll(int roomId){
        return reservationMapper.readAll();
    }

    public Reservation findById(int id){
        return reservationMapper.findById(id);
    }

    public int accept(int managerId, int id){
        char state = userMapper.findByUserId(id).getState();
        if(state == 'M'){
            int updatedLine = reservationMapper.accept(id);
            return updatedLine;
        } else {
            return -1;
        }
    }

    public int decline(int managerId, int id){
        char state = userMapper.findByUserId(id).getState();
        if(state == 'M'){
            int updatedLine = reservationMapper.decline(id);
            return updatedLine;
        } else {
            return -1;
        }
    }

}
