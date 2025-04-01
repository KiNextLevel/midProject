package controller.logic;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    private static final String user = "mquetest@gmail.com"; // gmail 계정
    private static final String password = ""; // gmail 패스워드

    public static void sendMail(String toEmail, String subject, String content) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        MimeMessage message = new MimeMessage(session);

        try {
            // 송신자 이메일 주소를 noreply로 설정
            message.setFrom(new InternetAddress("noreply@nextLove.com")); // noreply@example.com으로 변경

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            message.setSubject(subject);

            message.setText(content);

            // 이메일 송신
            Transport.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
