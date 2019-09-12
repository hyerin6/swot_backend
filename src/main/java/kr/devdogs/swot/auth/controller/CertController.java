package kr.devdogs.swot.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// email 인증 결과를 리다이렉트
@Controller
@RequestMapping("/auth/api")
public class CertController {

   // @Autowired
   // UserMapper userMapper;

    // 회원가입 시, 이메일 인증 및 state 변경
    @RequestMapping(value="/cert", method = RequestMethod.GET)
    public String emailCertification(@RequestParam("token") String token){
        //int result = userMapper.emailCertification(token);
        String fail = "http://localhost:8080/static/Fail.html";
        String success = "http://localhost:8080/static/Success.html";
        if(true) return "redirect:" + success;
        else return "redirect:" + fail;
    }

    // 비밀번호 변경
    @RequestMapping(value="/cert2", method = RequestMethod.GET)
    public String emailCertification2(@RequestParam("token") String token){
        //int result1 = userMapper.passwordUpdate(token);
        //int result2 = userMapper.emailCertification(token);
        String fail = "http://localhost:8080/static/Fail.html";
        String success = "http://localhost:8080/static/PwChangeSuccess.html";
        if(false) return "redirect:" + success;
        else return "redirect:" + fail;
    }
}