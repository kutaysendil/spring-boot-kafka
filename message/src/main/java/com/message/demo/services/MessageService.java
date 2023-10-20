package com.message.demo.services;

import com.message.demo.dto.SampleEvent;
import com.message.demo.dto.UserSavedEvent;
import com.message.demo.model.Message;
import com.message.demo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository repository;

    @KafkaListener(topics = "user.saved2")
    public void consume(UserSavedEvent event) {
        //Do what whatever you want
        log.warn("event handled usersaved :{}", event);
        Message message = new Message();
        message.setUserId(event.getUserId());
        message.setMessage(event.getMessage());
        repository.save(message);
    }

    @KafkaListener(topics = "sample.event")
    public void consume(SampleEvent item) {
        log.warn("event handled SampleEvent {}", item);

    }

}
