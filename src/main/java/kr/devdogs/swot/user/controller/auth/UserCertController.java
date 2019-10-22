package kr.devdogs.swot.user.controller.auth;

import kr.devdogs.swot.user.service.cert.UserCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth/cert")
public class UserCertController {

    @Autowired UserCertService userCertService;

    // 회원가입 시, 이메일 인증 및 state 변경
    @RequestMapping(value="singUp", method = RequestMethod.GET)
    public String emailCert(@RequestParam("token") String token){
        String success = "http://swot.devdogs.kr:8080/static/Success.html";
        String fail = "http://swot.devdogs.kr:8080/static/Fail.html";
        if(userCertService.emailCert(token) == true){
            userCertService.successSignUpCert(token);
            return "redirect:" + success;
        } else {
            return "redirect:" + fail;
        }
    }

    // 비밀번호 변경 시, 이메일 인증 및 state 변경
    @RequestMapping(value="modifyPw", method = RequestMethod.GET)
    public String emailCert2(@RequestParam("token") String token){
        String success = "http://swot.devdogs.kr:8080/static/ModifyPassword.html";
        String fail = "http://swot.devdogs.kr:8080/static/Fail.html";
        if(userCertService.emailCert(token) == true){
            userCertService.successModifyPwCert(token);
            return "redirect:" + success;
        } else {
            return "redirect:" + fail;
        }
    }

}