package kr.devdogs.swot.application.controller;

import kr.devdogs.swot.application.dto.Application;
import kr.devdogs.swot.application.service.ApplicationService;
import kr.devdogs.swot.board.dto.Board;
import kr.devdogs.swot.board.service.BoardService;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/study/application")
public class ApplicationController{

    @Autowired ApplicationService applicationService;
    @Autowired UserService userService;
    @Autowired BoardService boardService;

    // 예약
    @RequestMapping(value="create", method= RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest req, Application application){
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");
        application.setUserId(userId);

        List<Application> applications = applicationService.findByBoardId(application.getBoardId());
        for(Application a : applications){
            if(a.getUserId() == userId){
                res.put("result", "fail");
                res.put("error", "이미 신청한 스터디 입니다.");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
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

    @RequestMapping(value="{boardId}/list", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByBoardId(@PathVariable("boardId") int boardId) {
        Map<String, Object> res = new HashMap<String, Object>();

        List<Application> applications = applicationService.findByBoardId(boardId);
        List<User> users = new ArrayList<>();
        for(Application a : applications){
            users.add(userService.findByUserId(a.getUserId()));
        }

        if(applications != null){
            res.put("result", "success");
            res.put("applications", applications);
            res.put("users", users);
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    // 특정 스터디 정보 조회 (?)
    @RequestMapping(value="{id}", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<String, Object>();

        Application application = applicationService.findById(id);
        List<User> users = new ArrayList<>();
        users.add(userService.findByUserId(application.getUserId()));

        if(application != null){
            res.put("result", "success");
            res.put("application", application);
            res.put("users", users);
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @RequestMapping(value="/myAcceptStudy", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> myAcceptStudy(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");

        List<Application> applications = applicationService.myAcceptStudy(userId);
        List<User> users = new ArrayList<>();
        List<Board> boards = new ArrayList<>();
        for(Application a : applications){
            boards.add(boardService.find(a.getBoardId()));
            users.add(userService.findByUserId(a.getUserId()));
        }

        if(applications != null){
            res.put("result", "success");
            res.put("applications", applications);
            res.put("users", users);
            res.put("boards", boards);
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 이 부분에서 예약 정보 줄 떼 게시글 정보도 줘야함.
    @RequestMapping(value="/myStudy", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByUserId(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");

        List<Application> applications = applicationService.findByUserId(userId);
        List<User> users = new ArrayList<>();
        List<Board> boards = new ArrayList<>();
        for(Application a : applications){
            boards.add(boardService.find(a.getBoardId()));
            users.add(userService.findByUserId(a.getUserId()));
        }

        if(applications != null){
            res.put("result", "success");
            res.put("applications", applications);
            res.put("users", users);
            res.put("boards", boards);
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
