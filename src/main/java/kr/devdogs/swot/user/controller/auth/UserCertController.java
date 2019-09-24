package kr.devdogs.swot.user.controller;

import kr.devdogs.swot.user.service.cert.UserCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/auth")
public class UserCertController {

    @Autowired UserCertService userCertService;

    // 회원가입 시, 이메일 인증 및 state 변경
    @RequestMapping(value="/cert", method = RequestMethod.GET)
    public String emailCert(@RequestParam("certToken") String certToken){
        String fail = "http://swot.devdogs.kr:8080/static/Fail.html";
        String success = "http://swot.devdogs.kr:8080/static/Success.html";
        if(userCertService.emailCert(certToken) == true){
            return "redirect:" + success;
        } else {
            return "redirect:" + fail;
        }
    }

    // 비밀번호 변경 시, 이메일 인증 및 state 변경
    @RequestMapping(value="/cert2", method = RequestMethod.GET)
    public String emailCert2(@RequestParam("certToken") String certToken){
        String fail = "http://swot.devdogs.kr:8080/static/Fail.html";
        String success = "http://swot.devdogs.kr:8080/static/PwChangeSuccess.html";
        if(userCertService.emailCert2(certToken)) return "redirect:" + success;
        else return "redirect:" + fail;
    }

}