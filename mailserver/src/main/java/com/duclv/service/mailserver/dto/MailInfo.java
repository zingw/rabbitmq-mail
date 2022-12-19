package com.duclv.service.mailserver.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailInfo {
    private String receiver;
    private String subject;
    private String name;
    private String content;
}
