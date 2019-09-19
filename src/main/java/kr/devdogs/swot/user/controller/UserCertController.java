package kr.devdogs.swot.user.controller;

import kr.devdogs.swot.user.service.cert.UserCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// email 인증 결과를 리다이렉트
@Controller
@RequestMapping("/api/auth")
public class CertController {

    @Autowired UserCertService userCertService;

    // 회원가입 시, 이메일 인증 및 state 변경
    @RequestMapping(value="/cert", method = RequestMethod.GET)
    public String emailCert(@RequestParam("certToken") String certToken){
        String fail = "http://localhost:8080/static/Fail.html";
        String success = "http://localhost:8080/static/Success.html";
        if(userCertService.emailCert(certToken) == true){
            return "redirect:" + success;
        } else {
            return "redirect:" + fail;
        }
    }

    /*
    // 비밀번호 변경
    @RequestMapping(value="/cert2", method = RequestMethod.GET)
    public String emailCertification2(@RequestParam("token") String token){
        //int result1 = userMapper.passwordUpdate(token);
        //int result2 = userMapper.emailCertification(token);
        String fail = "http://swot.devdogs.kr/static/Fail.html";
        String success = "http://swot.devdogs.kr/static/PwChangeSuccess.html";
        if(false) return "redirect:" + success;
        else return "redirect:" + fail;
    }
 */

}