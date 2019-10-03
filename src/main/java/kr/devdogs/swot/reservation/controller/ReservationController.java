package kr.devdogs.swot.reservation.controller;

import kr.devdogs.swot.reservation.dto.Reservation;
import kr.devdogs.swot.reservation.service.ReservationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

// 특정 사용자 예약 목록 조회
// 특정 강의실(roomId) 예약 목록 조회
// 관리자를 위한 모든 예약 목록 조회 (기간이 지난거 안보여줌)
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired ReservationService reservationService;

    // 특정 사용자 예약 목록 조회 (userId)
    @RequestMapping(value="MyReserved", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> userByList(HttpServletRequest req){

        JSONObject sesssion = (JSONObject)req.getAttribute("session");
        String uid = sesssion.get("uid").toString();
        List<Reservation> reservations = reservationService.findByUserId(uid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
