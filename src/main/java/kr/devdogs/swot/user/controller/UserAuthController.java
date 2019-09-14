package kr.devdogs.swot.user.controller;

import kr.devdogs.swot.security.jwt.JwtServiceImpl;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.service.UserAuthService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/* USER
1. 회원가입
2. 로그인
3. 비밀번호 변경
 */
@RestController
@RequestMapping(value = "/auth/user")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    // 회원가입
    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public JSONObject signup(User user){
        JSONObject JSON = new JSONObject();
        System.out.println("회원가입");
        if(user.getEmail() == null ||
        user.getName() == null ||
        user.getPassword() == null ||
        user.getPhone() == null) {
            JSON.put("statusCode", HttpStatus.BAD_REQUEST);
            JSON.put("statusMsg", "Email, Password, Name, Phone is Required");
            return JSON;
        }

        if(userAuthService.isEmailDuplicate(user)){
            JSON.put("statusCode", HttpStatus.CONFLICT);
            JSON.put("statusMsg", "이메일이 중복됩니다.");
            return JSON;
        }

        if(userAuthService.userSignup(user)) {
            JSON.put("statusCode", HttpStatus.OK);
            JSON.put("statusMsg", "SignUp Success");
            return JSON;
        }else{
            JSON.put("statusCode", "520");
            JSON.put("statusMsg", "Unknown Error");
            return JSON;
        }
    }

    // 로그인
    @RequestMapping(value="/signin", method=RequestMethod.POST)
    public JSONObject signin(User user){
        JSONObject JSON = new JSONObject();

        if(user.getEmail() == null ||
                user.getPassword() == null){
            JSON.put("statusCode", HttpStatus.BAD_REQUEST);
            JSON.put("statusMsg", "Email, Password is Required");
            return JSON;
        }

        User currentMember = userAuthService.userSignin(user);
        if(currentMember != null) {
            JSON.put("statusCode", HttpStatus.OK);
            JSON.put("statusMsg", "SignIn Success");
            JSON.put("accessToken", jwtServiceImpl.accessToken(user.getEmail()));
            JSON.put("refreshToken", currentMember.getRefreshToken());
            return JSON;
        } else {
            JSON.put("statusCode", "520");
            JSON.put("statusMsg", "Unknown Error");
            return JSON;
        }
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public JSONObject test(@RequestParam String id){
        JSONObject JSON = new JSONObject();
        JSON.put("id", id);
        System.out.println(JSON);
        return JSON;

    }


}
