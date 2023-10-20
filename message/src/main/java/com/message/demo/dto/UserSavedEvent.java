package com.message.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSavedEvent {

    private Long userId;
    private String message;
}
