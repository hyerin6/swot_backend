package kr.devdogs.swot.classroom.controller;


import kr.devdogs.swot.classroom.dto.Classroom;
import kr.devdogs.swot.classroom.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/classroom")
public class ClassroomAuthController {

    @Autowired ClassroomService classroomService;

    // 강의실 모든 Data 조회
    @RequestMapping(value="list", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> readAll(){
        Map<String, Object> res = new HashMap<String, Object>();

        List<Classroom> currentClass = classroomService.readAll();
        if(currentClass.isEmpty()){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", currentClass);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 특정 강의실 Data 조회
    @RequestMapping(value="{id}", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> read(@PathVariable("id") int id){
        Map<String, Object> res = new HashMap<String, Object>();

        Classroom currentClass = classroomService.findById(id);
        if(currentClass == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", currentClass);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
