package com.kafka.demo.service;

import com.kafka.demo.dto.UserDto;
import com.kafka.demo.dto.UserProducerObj;
import com.kafka.demo.model.User;
import com.kafka.demo.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRespository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public User saveUser(UserDto dto) {

        // Userin olup olmama durumunu kontrol edebilirsin veya o mailin dha önce kullanıp kullanılmadığını bütün actionlardan success aldıktan sonra devam edersin.
        User user = new User();
        user.setUsername(dto.getUsername());

        User savedUser = repository.save(user);
        kafkaTemplate.send("user.saved2", "User saved", UserProducerObj.builder()
                .userId(savedUser.getId())
                .message("User başarıyla kaydedildi.")
                .build());
        log.info("event sent by UserService");

        return savedUser;

    }

    public String throwNewKafka() {
        kafkaTemplate.send("sample.event", "user uyarıldı", UserDto.builder()
                .username("hüsameddin şah aladdin kırteke").build());
        return "Event sent by DemoKafkaService";
    }
}
