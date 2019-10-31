package kr.devdogs.swot.reservation.service;

import kr.devdogs.swot.reservation.dto.Reservation;
import kr.devdogs.swot.reservation.mapper.ReservationMapper;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService{
    @Autowired ReservationMapper reservationMapper;
    @Autowired UserMapper userMapper;

    @Override
    public int create(Reservation reservation){
        reservationMapper.create(reservation);
        reservation.getId();
        return reservation.getId();
    }

    @Override
    public int delete(int id){
        return reservationMapper.delete(id);
    }

    @Override
    public List<Reservation> findByUserId(int userId){
        return reservationMapper.findByUserId(userId);
    }

    @Override
    public List<Reservation> findByRoomId(int roomId){
        return reservationMapper.findByRoomId(roomId);
    }

    @Override
    public List<Reservation> readAll(){
        return reservationMapper.readAll();
    }

    @Override
    public Reservation findById(int id){
        return reservationMapper.findById(id);
    }

    @Override
    public int accept(int managerId, int id){
        char state = userMapper.findByUserId(id).getState();
        if(state == 'M'){
            int updatedLine = reservationMapper.accept(id);
            return updatedLine;
        } else {
            return -1;
        }
    }

    @Override
    public int decline(int managerId, int id){
        char state = userMapper.findByUserId(managerId).getState();
        if(state == 'M'){
            int updatedLine = reservationMapper.decline(id);
            return updatedLine;
        } else {
            return -1;
        }
    }

    @Override
    public boolean auth(int managerId){
        User manager = userMapper.findByUserId(managerId);
        if(manager.getState() == 'M'){
            return true;
        }
        return false;
    }

}
