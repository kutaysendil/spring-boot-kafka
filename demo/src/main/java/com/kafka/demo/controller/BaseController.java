package com.kafka.demo.controller;

import com.kafka.demo.dto.UserDto;
import com.kafka.demo.model.User;
import com.kafka.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BaseController {

    private final UserService userService;

    @PostMapping
    public User saveUser(@RequestBody UserDto dto) {
        return userService.saveUser(dto);
    }

    @GetMapping
    public String throwNewKafka() {
        return userService.throwNewKafka();
    }
}


