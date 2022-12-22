package com.duclv.service.mailserver.consumer;

import com.duclv.service.mailserver.config.MailService;
import com.duclv.service.mailserver.dto.MailInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailConsumer {

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "MAIL_QUEUE")
    public void getDetail(MailInfo mailInfo) {
//        mailService.sendEmail(mailInfo);
//        throw new RuntimeException("error!");
        System.out.println("1" + mailInfo.toString());
    }

    @RabbitListener(queues = "SMS_QUEUE")
    public void getMailInfoFromQueue(MailInfo mailInfo) {
//        mailService.sendEmail(mailInfo);
//        throw new RuntimeException("error!");
        System.out.println("2" + mailInfo.toString());
    }

//    @RabbitListener(queues = "MAIL_QUEUE")
//    public void getMailInfoFromQueueVVV(MailInfo mailInfo) {
////        mailService.sendEmail(mailInfo);
////        throw new RuntimeException("error!");
//        System.out.println("2" + mailInfo.toString());
//    }


}
