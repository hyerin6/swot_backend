package kr.devdogs.swot.classroom.controller;

import kr.devdogs.swot.classroom.dto.Classroom;
import kr.devdogs.swot.classroom.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 관리자가 Create, Update, Delete 한다.
// 사용자와 관리자를 위한 Read 필요
@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    @Autowired ClassroomService classroomService;

    // 강의실 생성
    @RequestMapping(value="create", method= RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest req, Classroom classroom
    ){
        Map<String, Object> res = new HashMap<String, Object>();
        // 매니저만 강의실을 생성할 수 있다.
        int managerId = (int) req.getAttribute("session");

        if(classroom.getGroupNo() == 0 ||
                classroom.getRoomNo() == null ||
                classroom.getRoomName() == null){
            res.put("result", "fail");
            res.put("error", "classroom Data is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        if(classroomService.auth(managerId) == false){
            res.put("result", "fail");
            res.put("error", "관리자가 아닙니다.");
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }

        int id = classroomService.create(classroom);
        Classroom currentClass = classroomService.findById(id);
        if(currentClass != null) {
            res.put("result", "success");
            res.put("info", currentClass);
        }else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 강의실 정보 update - groupNo, roomNo, roomName, state, total
    @RequestMapping(value="modify/{id}", method= RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> modify(@PathVariable("id") int id,
                                                      HttpServletRequest req,
                                                      Classroom classroom){
        Map<String, Object> res = new HashMap<String, Object>();
        int managerId = (int) req.getAttribute("session");

        if(classroomService.auth(managerId) == false){
            res.put("result", "fail");
            res.put("error", "관리자가 아닙니다.");
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }

        classroom.setId(id);
        Classroom currentClass = classroomService.modify(classroom);
        if(currentClass == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", currentClass);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 특정 강의실 삭제
    @RequestMapping(value="delete/{id}", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int id, HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();
        int managerId = (int) req.getAttribute("session");

        if(classroomService.auth(managerId) == false){
            res.put("result", "fail");
            res.put("error", "관리자가 아닙니다.");
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }

        if(classroomService.delete(id)){
            res.put("result", "success");
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
