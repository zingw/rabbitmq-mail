package com.example.emailpublisher.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailInfo {
    private String receiver;
    private String subject;
    private String name;
    private String content;
}
