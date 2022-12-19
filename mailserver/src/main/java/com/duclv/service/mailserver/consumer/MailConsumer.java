package com.duclv.service.mailserver.consumer;

import com.duclv.service.mailserver.config.MailService;
import com.duclv.service.mailserver.dto.MailInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MailConsumer {
    @Autowired
    MailService mailService;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "MAIL_QUEUE")
    public void getMailInfoFromQueue(MailInfo mailInfo) {
        mailService.sendEmail(mailInfo);
    }
}
