package kr.devdogs.swot.classroom.controller;

import kr.devdogs.swot.classroom.dto.Classroom;
import kr.devdogs.swot.classroom.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Object>> createClassroom(HttpServletRequest req, Classroom classroom
    ){
        Map<String, Object> res = new HashMap<String, Object>();
        // 매니저만 강의실을 생성할 수 있다.
        String managerId = (String) req.getAttribute("session");

        if(classroom.getGroup() == null ||
        classroom.getGroup_no() == 0 ||
        classroom.getRoom_no() == null ||
        classroom.getTotal() == 0){
            res.put("result", "fail");
            res.put("error", "classroom data is Required");
        }

        Classroom currentClass = classroomService.create(managerId, classroom);
        if(currentClass != null) {
            res.put("result", "success");
            res.put("info", currentClass);
        }else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
