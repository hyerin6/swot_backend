package kr.devdogs.swot.user.controller;

import kr.devdogs.swot.security.jwt.JwtService;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.service.auth.UserAuthService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/* USER
1. 회원가입
2. 로그인
3. 비밀번호 변경
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/auth")
public class UserAuthController {

    @Autowired private UserAuthService userAuthService;
    @Autowired private JwtService jwtService;

    // 회원가입
    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public JSONObject signup(User user){
        JSONObject jsonObject = new JSONObject();
        if(user.getEmail() == null ||
        user.getName() == null ||
        user.getPassword() == null ||
        user.getPhone() == null) {
            jsonObject.put("statusCode", HttpStatus.BAD_REQUEST);
            jsonObject.put("statusMsg", "Email, Password, Name, Phone is Required");
            return jsonObject;
        }

        if(userAuthService.isEmailDuplicate(user)){
            jsonObject.put("statusCode", "SIGNUP FAIL");
            jsonObject.put("statusMsg", "이메일이 중복됩니다.");
            return jsonObject;
        }

        if(userAuthService.userSignup(user)) {
            jsonObject.put("statusCode", HttpStatus.OK);
            jsonObject.put("statusMsg", "success");
            return jsonObject;
        }else{
            jsonObject.put("statusCode", "SIGNUP FAIL");
            jsonObject.put("statusMsg", "Unknown Error");
            return jsonObject;
        }
    }

    // 로그인
    @RequestMapping(value="/signin", method=RequestMethod.POST)
    public JSONObject signin(User user){
        JSONObject jsonObject = new JSONObject();

        if(user.getEmail() == null ||
                user.getPassword() == null){
            jsonObject.put("statusCode", HttpStatus.BAD_REQUEST);
            jsonObject.put("statusMsg", "Email, Password is Required");
            return jsonObject;
        }

        User currentUser = userAuthService.userSignin(user);
        if(currentUser != null) {
            jsonObject.put("statusCode", HttpStatus.OK);
            jsonObject.put("statusMsg", "success");
            jsonObject.put("accessToken", jwtService.accessToken(user.getEmail()));
            jsonObject.put("refreshToken", currentUser.getRefreshToken());
            return jsonObject;
        } else {
            jsonObject.put("statusCode", "SIGNIN FAIL");
            jsonObject.put("statusMsg", "Unknown Error");
            return jsonObject;
        }
    }

    // 비밀번호 잊어버림, 새로 입력받기
    @RequestMapping(value="/inputNewPw", method=RequestMethod.POST)
    public JSONObject inputNewPw(User user){
        JSONObject jsonObject = new JSONObject();

        boolean result = userAuthService.tempPasswordUpdate(user);
        if(result){
            jsonObject.put("statusCode","");
            jsonObject.put("statusMsg","");
            return jsonObject;
        } else{
            jsonObject.put("statusCode","");
            jsonObject.put("statusMsg","");
            return jsonObject;
        }

    }

}
