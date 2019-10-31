package kr.devdogs.swot.reservation.controller;

import kr.devdogs.swot.reservation.dto.Reservation;
import kr.devdogs.swot.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/reservation")
public class ReservationAuthController {

    @Autowired ReservationService reservationService;

    // 전체 예약 목록 조회
    @RequestMapping(value = "list", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> readAll(){
        Map<String, Object> res = new HashMap<String, Object>();
        List<Reservation> reservations = reservationService.readAll();
        if(reservations == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", reservations);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    // 특정 강의실 예약 목록 조회
    @RequestMapping(value="roomReserved/{roomId}", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("roomId") int roomId){
        Map<String, Object> res = new HashMap<String, Object>();
        List<Reservation> reservations = reservationService.findByRoomId(roomId);
        if(reservations == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", reservations);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
