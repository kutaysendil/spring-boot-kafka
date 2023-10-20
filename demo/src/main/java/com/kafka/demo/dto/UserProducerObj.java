package com.kafka.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserProducerObj {
    private Long userId;
    private String message;
}
