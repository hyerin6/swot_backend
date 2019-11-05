package kr.devdogs.swot.application.controller;

import kr.devdogs.swot.application.dto.Application;
import kr.devdogs.swot.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/study/application")
public class ApplicationController{

    @Autowired ApplicationService applicationService;

    // 예약
    @RequestMapping(value="create", method= RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest req, Application application){
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");
        application.setUserId(userId);

        if(application.getApplicationDate() == null ||
        application.getEndTime() == null ||
        application.getStartTime() == null){
            res.put("result", "fail");
            res.put("error", "Application Data is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        if(applicationService.create(application) == 1){
            res.put("result", "success");
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 수락
    @RequestMapping(value="accept/{id}", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> accept(@PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<String, Object>();

        if(applicationService.accept(id) == 1){
            res.put("result", "success");
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 거절
    @RequestMapping(value="decline/{id}", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> decline(@PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<String, Object>();

        if(applicationService.decline(id) == 1){
            res.put("result", "success");
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value="/{boardId}/list", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByBoardId(@PathVariable("boardId") int boardId) {
        Map<String, Object> res = new HashMap<String, Object>();

        List<Application> applications = applicationService.findByBoardId(boardId);

        if(applications != null){
            res.put("result", "success");
            res.put("info", applications);
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /*
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<String, Object>();

        Application application = applicationService.findById(id);

        if(application != null){
            res.put("result", "success");
            res.put("info", application);
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
     */

    @RequestMapping(value="/myAcceptStudy", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> myAcceptStudy(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");

        List<Application> applications = applicationService.myAcceptStudy(userId);

        if(applications != null){
            res.put("result", "success");
            res.put("info", applications);
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value="/myStudy", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByUserId(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");

        List<Application> applications = applicationService.findByUserId(userId);

        if(applications != null){
            res.put("result", "success");
            res.put("info", applications);
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    // 스터디 취소
    @RequestMapping(value="/delete/{id}", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<String, Object>();

        if(applicationService.delete(id) == 1){
            res.put("result", "success");
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
