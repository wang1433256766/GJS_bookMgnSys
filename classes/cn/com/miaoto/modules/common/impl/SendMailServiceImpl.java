package cn.com.miaoto.modules.common.impl;

import cn.com.miaoto.common.SystemSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by hx on 2017/8/15.
 */
@Service
@EnableAsync
public class SendMailServiceImpl {

    public static final Logger LOGGER = LoggerFactory.getLogger(SendMailServiceImpl.class);

    @Resource
    SystemSetting systemSetting;

    @Async
    public void SEND_SUBMIT_EMAIL(String toMail, String subject, String context) {
        Properties props = new Properties();
        props.put("mail.smtp.host", SystemSetting.getValue("smtpHost", String.class));
        props.put("mail.smtp.socketFactory.port", SystemSetting.getValue("smtpSocketFactoryPort", String.class));
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SystemSetting.getValue("smtpPort", String.class));
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SystemSetting.getValue("email", String.class), SystemSetting.getValue("emailPassword", String.class));
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();

            message.setFrom(new InternetAddress(SystemSetting.getValue("email", String.class)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            message.setSubject(subject);

            htmlPart.setContent(context, "text/html; charset=utf-8");
            htmlPart.setHeader("Content-Type", "text/html");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            message.saveChanges();

            Transport.send(message);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LOGGER.debug("from = {}, to = {}, subject = {}, time = {}", SystemSetting.getValue("email", String.class), toMail, subject, df.format(new Date()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
