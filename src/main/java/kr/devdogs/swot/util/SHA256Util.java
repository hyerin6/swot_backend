package kr.devdogs.swot.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHA256Util{

    public static String getEncrypt(String source) {

        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(source.getBytes());
            byte bs[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bs.length; i++) {
                sb.append(Integer.toString((bs[i] & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {

        } catch (Exception e) {

        }
        return result;
    }
}
