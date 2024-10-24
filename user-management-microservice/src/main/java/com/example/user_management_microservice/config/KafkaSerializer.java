package com.example.user_management_microservice.config;

import com.example.user_management_microservice.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class KafkaSerializer implements Serializer<User> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, User data) {
        if (data == null) {
            return null;  // Return null if the object is null
        }

        try {
            // Convert the object to JSON and then to bytes
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            // Handle the exception (you might want to log this)
            e.printStackTrace();
            return null;  // Return null in case of an error
        }
    }




}

