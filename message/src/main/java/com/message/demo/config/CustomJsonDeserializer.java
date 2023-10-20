package com.message.demo.config;

import com.google.gson.Gson;
import com.message.demo.dto.SampleEvent;
import com.message.demo.dto.UserSavedEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CustomJsonDeserializer implements Deserializer<Object> {


    private final Map<String, Class<?>> maps;

    public CustomJsonDeserializer() {
        maps = new HashMap<>(2);
        maps.put("sample.event", SampleEvent.class);
        maps.put("user.saved2", UserSavedEvent.class);
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        if (Objects.isNull(data) || data.length == 0) {
            return null;
        }
        String json = null;
        try {
            json = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        return gson.fromJson(json, maps.get(topic));

    }
}