package com.example.emailpublisher.controller;

import com.example.emailpublisher.config.RabbitConfig;
import com.example.emailpublisher.dto.MailInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MailPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendSampleEmailRabbitMQ(@RequestBody MailInfo mailInfo) {
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE, RabbitConfig.ROUTING_KEY, mailInfo);
        return ResponseEntity.ok().body("message sent success to " + mailInfo.getReceiver());
    }

}
