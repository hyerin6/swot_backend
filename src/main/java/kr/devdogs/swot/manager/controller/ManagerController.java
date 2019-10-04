package kr.devdogs.swot.manager.controller;

import kr.devdogs.swot.manager.dto.Manager;
import kr.devdogs.swot.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 관리자 마이페이지
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired ManagerService managerService;

    /*
    @RequestMapping(value="myinfo", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getMyInfo(HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();

        String id = (String) req.getAttribute("session");
        Manager currentManager = managerService.getMyInfo(id);

        if(currentManager != null){
            res.put("result", "success");
            res.put("info", currentManager);
        }else{
            res.put("result", "fail");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    */


}
