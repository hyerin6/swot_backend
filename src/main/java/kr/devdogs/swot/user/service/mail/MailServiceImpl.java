package kr.devdogs.swot.user.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service("mailService")
public class MailServiceImpl implements MailService{
    @Autowired
    JavaMailSender emailSender;

    @Override
    public String send(String email, int cert){
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
            String text = "";
            if(cert == 1){ // 회원가입
                text = "<html><body><p>인증을 위해 아래 링크를 클릭해 주세요.</p>" +
                        "<p>이메일 인증 후 로그인이 가능합니다.</p>" +
                        "<a href='http://swot.devdogs.kr:8080/api/auth/cert/singUp?&token=" + token + "'>여기를 눌러 인증을 완료해주세요.</a></body></html>";
            } else if(cert == 2){ // 비밀번호 변경
                text = "<html><body><p>인증을 위해 아래 링크를 클릭해 주세요.</p>" +
                        "<p>이메일 인증 후 로그인이 가능합니다.</p>" +
                        "<a href='http://swot.devdogs.kr:8080/api/auth/cert/modifyPw?&token=" + token + "'>여기를 눌러 인증을 완료해주세요.</a></body></html>";
            }
            helper.setTo(email);
            helper.setSubject("SWOT 인증 메일입니다.");
            helper.setText(text, true);
            emailSender.send(msg);
        } catch (MessagingException e) {
            // LOG.error("Error", e);
        } catch (MailAuthenticationException e){
            //LOG.error("Error", e);
        } catch (HttpServerErrorException.InternalServerError e){
            //LOG.error("Error", e);
        }
        return token;
    }


}
