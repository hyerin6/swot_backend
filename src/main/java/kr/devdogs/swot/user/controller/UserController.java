package kr.devdogs.swot.user.controller;

import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.service.UserService;
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

/*
UserController 에서는
회원의 인증 토큰이 유효한지 검사해야하기 때문에
JwtInterceptor 을 거쳐서 인증을 받아야한다.
*/
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired UserService userService;

    // 마이페이지
    @RequestMapping(value ="myinfo", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getMyInfo(HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();

        String email = (String) req.getAttribute("session");
        User currentUser = userService.getUser(email);

        if(currentUser != null){
            res.put("result", "success");
            res.put("info", currentUser);
        }else{
            res.put("result", "fail");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 회원 탈퇴
    @RequestMapping(value ="withdraw", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> userWithdraw(HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();

        String email = (String) req.getAttribute("session");

        boolean result = userService.withdraw(email);
        if(result){
            res.put("result", "success");
        }else{
            res.put("result", "fail");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 정보 수정
    @RequestMapping(value="modifyMyinfo", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> userInfoUpdate(User user){
        Map<String, Object> res = new HashMap<String, Object>();

        User currentUser = userService.modifyMyinfo(user);
        if(currentUser != null){
            res.put("result", "success");
            res.put("info", currentUser);
        }else{
            res.put("result", "fail");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
