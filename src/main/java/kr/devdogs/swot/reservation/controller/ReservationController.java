package kr.devdogs.swot.reservation.controller;

import kr.devdogs.swot.reservation.dto.Reservation;
import kr.devdogs.swot.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// user - 강의실 예약
// user - 강의실 예약 취소
// manager - 강의실 예약 수락 및 거절

// 특정 사용자 예약 목록 조회
// 특정 강의실(roomId) 예약 목록 조회
// 관리자를 위한 모든 예약 목록 조회 (기간이 지난거 안보여줌)
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired ReservationService reservationService;

    // user - 강의실 예약
    @RequestMapping(value="create", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> createReservation(HttpServletRequest req, Reservation reservation) {
        Map<String, Object> res = new HashMap<String, Object>();

        String userUid = (String) req.getAttribute("session");
        reservation.setUserId(userUid);

        boolean result = reservationService.createReservation(reservation);

        if(result) {
            res.put("result", "success");
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 특정 사용자 예약 목록 조회 (userId)
    @RequestMapping(value="MyReserved", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> userByList(HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();
        String uid = (String) req.getAttribute("session");
        List<Reservation> reservations = reservationService.findByUserId(uid);



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
