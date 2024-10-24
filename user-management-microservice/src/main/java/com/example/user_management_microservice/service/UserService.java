package com.example.user_management_microservice.service;


import com.example.user_management_microservice.entity.User;
import com.example.user_management_microservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final String TOPIC = "user-events";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public User registerUser(User user) {
        // Hash password before saving (use BCrypt or similar)
        User savedUser = userRepository.save(user);
        kafkaTemplate.send(TOPIC, "User Registered: " + user.getUsername());
        return savedUser;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword()); // Update after password hash
        kafkaTemplate.send(TOPIC, "User Updated: " + user.getUsername());
        return userRepository.save(user);
    }

    public User updateUserRoles(Long id, String role) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUserRole(role);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        kafkaTemplate.send(TOPIC, "User Deleted with ID: " + id);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
        kafkaTemplate.send(TOPIC, "all users deleted");
    }

}
