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

//    public static void main(String[] args) throws JsonProcessingException {
//        Map<String,String> params = new HashMap<>();
//        params.put("name","Tho");
//        params.put("age","23");
//        params.put("favor","sex");
//        MailInfo mailInfo = new MailInfo("viettho.bk92", "duclv",params);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String mailJson = objectMapper.writeValueAsString(mailInfo);
//
//        MailInfo mailInfoAfter = objectMapper.readValue(mailJson, MailInfo.class);
//        System.out.println(mailInfoAfter.getParams().get("name"));
//        System.out.println(mailInfoAfter.getParams().get("age"));
//        System.out.println(mailInfoAfter.getParams().get("favor"));
//        System.out.println("Ok");
//    }
}
