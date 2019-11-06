package kr.devdogs.swot.reservation.controller;

import kr.devdogs.swot.reservation.dto.Reservation;
import kr.devdogs.swot.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// user - 강의실 예약
// user - 강의실 예약 취소
// manager - 강의실 예약 수락 및 거절

// 특정 사용자 예약 목록 조회

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired ReservationService reservationService;

    // user - 강의실 예약
    @RequestMapping(value="create/{roomId}", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest req,
                                                      @PathVariable("roomId") int roomId,
                                                      Reservation reservation){
        Map<String, Object> res = new HashMap<String, Object>();

        if(reservation.getPhone() == null ||
        reservation.getReason() == null ||
        reservation.getTotal() == 0 ||
        reservation.getStartTime() == null ||
        reservation.getEndTime() == null ||
        reservation.getReservationDate() == null ||
        reservation.getStudentId() == null ||
        reservation.getStudentName() == null){
            res.put("result", "fail");
            res.put("error", "reservation Data is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        int userId = (int) req.getAttribute("session");

        reservation.setRoomId(roomId);
        reservation.setUserId(userId);

        int id = reservationService.create(reservation);
        Reservation currentReservation = reservationService.findById(id);

        if(reservation != null) {
            res.put("result", "success");
            res.put("info", currentReservation);
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 특정 사용자 예약 목록 조회 (userId)
    @RequestMapping(value="MyReserved", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByUserId(HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");
        List<Reservation> reservations = reservationService.findByUserId(userId);
        if(reservations == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", reservations);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    // 사용자 - 예약 삭제
    @RequestMapping(value="delete/{id}", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> delete(HttpServletRequest req, @PathVariable("id") int id){
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");
        int updatedLine = reservationService.delete(id);
        if(updatedLine == 1){
            res.put("result", "success");
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 관리자 - 예약 수락
    @RequestMapping(value="accept/{id}", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> accept(HttpServletRequest req, @PathVariable("id") int id){
        Map<String, Object> res = new HashMap<String, Object>();
        int managerId = (int) req.getAttribute("session");

        if(reservationService.auth(managerId) == false){
            res.put("result", "fail");
            res.put("error", "관리자가 아닙니다.");
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }

        int updatedLine = reservationService.accept(managerId, id);
        if(updatedLine == 1){
            res.put("result", "success");
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 관리자 - 예약 거절
    @RequestMapping(value="decline/{id}", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> decline(HttpServletRequest req, @PathVariable("id") int id, String failReason){
        Map<String, Object> res = new HashMap<String, Object>();
        int managerId = (int) req.getAttribute("session");

        if(reservationService.auth(managerId) == false){
            res.put("result", "fail");
            res.put("error", "관리자가 아닙니다.");
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }

        int updatedLine = reservationService.decline(managerId, id, failReason);
        if(updatedLine == 1){
            res.put("result", "success");
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
