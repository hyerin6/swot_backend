package kr.devdogs.swot.user.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service("MailService")
public class MailServiceImpl implements MailService{
    @Autowired
    JavaMailSender emailSender;

    @Override
    public String send(String email){
        char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();
        // 토큰 생성
        Random rn = new Random();
        for( int i = 0 ; i < 13 ; ++i ){
            sb.append( charaters[ rn.nextInt( charaters.length ) ] );
        }
        String token = sb.toString();
        // 이메일을 보냄
        MimeMessage msg = emailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(msg, false, "UTF-8");
            String text = "<html><body><p>인증을 위해 아래 링크를 클릭해 주세요</p>" +
                    "<a href='http://localhost:8080/api/cert/?&token=" + token + "'>여기를 눌러 인증해주세요</a></body></html>";
            helper.setTo(email);
            helper.setSubject("SKHU 강의실 예약 회원가입 - 이메일 인증");
            helper.setText(text,true);
            emailSender.send(msg);
        } catch (MessagingException e) {
            // 에러 LOG는 나중에 처리
            // LOG.error("Internal server Error", e);
        }
        return token;
    }
}
