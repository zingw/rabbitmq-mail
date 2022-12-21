package com.duclv.service.mailserver.config;

import com.duclv.service.mailserver.dto.MailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    TemplateEngine templateEngine;

    public void sendEmail(MailInfo mailInfo) {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("name",mailInfo.getName());
        context.setVariable("content",mailInfo.getContent());
        String text = templateEngine.process("greeting.html", context);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("vietduc.bk69@gmail.com");
            helper.setTo(mailInfo.getReceiver());
            helper.setSubject(mailInfo.getSubject());
            helper.setText(text,true);

        } catch (
                MessagingException e) {
            throw new MailParseException(e);
        }
        javaMailSender.send(message);
    }
}
