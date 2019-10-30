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
1. 회원가입 (관리자는 회원가입 없음)
2. 로그인
3. 비밀번호 변경
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth/user")
public class UserAuthController {

    @Autowired private UserAuthService userAuthService;
    @Autowired private JwtService jwtService;

    // 회원가입
    @RequestMapping(value="signUp", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> signUp(User user){
        Map<String, Object> res = new HashMap<String, Object>();

        // 이메일, 비밀번호, 이름, 학번, 전화번호 입력하지 않은 경우
        if(user.getEmail() == null ||
        user.getName() == null ||
        user.getPw() == null ||
        user.getStudentId() == null ||
        user.getPhone() == null) {
            res.put("result", "fail");
            res.put("error", "Email, Password, Name, StudentId, Phone is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 이메일 중복되는 경우
        if(userAuthService.isEmailDuplicate(user)){
            res.put("result", "fail");
            res.put("error", "이메일이 중복됩니다.");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        if(userAuthService.userSignUp(user)) {
            res.put("result", "success");
        }else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value="emailDuplicate", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> emailDuplicate(String email) {
        Map<String, Object> res = new HashMap<String, Object>();

        User user = new User();
        user.setEmail(email);

        // 이전에 탈퇴했던 이메일도 사용 불가
        if(userAuthService.isEmailDuplicate(user)){
            res.put("result", "이메일 중복입니다.");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            res.put("result", "사용 가능한 이메일 입니다.");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 로그인
    @RequestMapping(value="signIn", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> signIn(User user){
        Map<String, Object> res = new HashMap<String, Object>();

        // Email, Password 중 빠진게 있으면 로그인할 수 없음
        if(user.getEmail() == null ||
                user.getPw() == null){
            res.put("result", "fail");
            res.put("error", "Email, Password is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 입력한 정보로 회원 조회
        User currentUser = userAuthService.userSignIn(user);
        char state = currentUser.getState();

        if(state == 'C'){ // 이메일 인증하지 않은 회원인 경우
            res.put("result", "fail");
            res.put("error", "Email unauthenticated");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else if(state == 'T' || state == 'M'){
            // 회원 인증이 완료된 경우 success 결과와 token 을 발급해준다.
                res.put("result", "success");
                res.put("token", jwtService.accessToken(currentUser.getId()));
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 비밀번호 변경
    @RequestMapping(value="modifyPw", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> passwordUpdate(User user){
        Map<String, Object> res = new HashMap<String, Object>();

        // 이메일과 비밀번호 입력하지 않은 경우
        if(user.getEmail() == null || user.getModifyPw() == null){
            res.put("result", "fail");
            res.put("error", "Email, new password is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 사용자가 입력한 새로운 비밀번호를 DB에 저장하고 state 변경
        boolean result = userAuthService.modifyPw(user);
        if(result){
            res.put("result", "success");
        } else{
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
