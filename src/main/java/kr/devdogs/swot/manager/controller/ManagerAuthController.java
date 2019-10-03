package kr.devdogs.swot.manager.controller;

import kr.devdogs.swot.manager.dto.Manager;
import kr.devdogs.swot.manager.service.auth.ManagerAuthService;
import kr.devdogs.swot.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// 로그인
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth/manager")
public class ManagerAuthController {
    @Autowired JwtService jwtService;
    @Autowired ManagerAuthService managerAuthService;

    @RequestMapping(value="signin", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> managerSignin(Manager manager){
        Map<String, Object> res = new HashMap<String, Object>();
        if(manager.getId() == null ||
                manager.getPassword() == null){
            res.put("error", "ID, Password is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        Manager currentManager = managerAuthService.managerSignin(manager);
        if(currentManager != null) {
            res.put("result", "success");
            res.put("token", jwtService.accessToken(currentManager.getId()));
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
