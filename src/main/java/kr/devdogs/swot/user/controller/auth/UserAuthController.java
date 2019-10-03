package kr.devdogs.swot.user.controller.auth;

import kr.devdogs.swot.security.jwt.JwtService;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.service.auth.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/* user
1. 회원가입
2. 로그인
3. 비밀번호 변경
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/auth/user")
public class UserAuthController {

    @Autowired private UserAuthService userAuthService;
    @Autowired private JwtService jwtService;

    // 회원가입
    @RequestMapping(value="signup", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> signup(User user){
        Map<String, Object> res = new HashMap<String, Object>();
        // Email, Password, Name, Major_no 중 빠진게 있으면 회원가입 안됨
        if(user.getEmail() == null ||
        user.getName() == null ||
        user.getPassword() == null ||
        user.getMajor_no() == null) {
            res.put("error", "Email, Password, Name, Major_no is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        // 이메일 중복 검사
        if(userAuthService.isEmailDuplicate(user)){
            res.put("result", "fail");
            res.put("error", "이메일이 중복됩니다.");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        if(userAuthService.userSignup(user)) {
            res.put("result", "success");
        }else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 로그인
    @RequestMapping(value="signin", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> signin(User user){
        Map<String, Object> res = new HashMap<String, Object>();
        // Email, Password 중 빠진게 있으면 로그인할 수 없음
        if(user.getEmail() == null ||
                user.getPassword() == null){
            res.put("error", "Email, Password is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        // 입력한 정보로 회원 조회
        User currentUser = userAuthService.userSignin(user);
        if(currentUser != null) {
            res.put("result", "success");
            res.put("accessToken", jwtService.accessToken(user.getEmail()));
            res.put("refreshToken", jwtService.refreshToken(user.getEmail()));
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 비밀번호 변경
    @RequestMapping(value="modifyPassword", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> passwordUpdate(User user){
        Map<String, Object> res = new HashMap<String, Object>();

        if(user.getTempPassword() == null){
            res.put("error", "new password is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 사용자에게 이메일과 새로운 비밀번호를 입력받는다.
        boolean result = userAuthService.tempPasswordUpdate(user);
        if(result){
            res.put("result", "success");
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
