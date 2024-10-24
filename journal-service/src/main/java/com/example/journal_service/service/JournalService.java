package com.example.journal_service.service;

import com.example.journal_service.entity.Journal;
import com.example.journal_service.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @KafkaListener(topics = "user-events", groupId = "journal-service-group")
    public void consumeUserEvent(String event) {
        Journal journal = new Journal();
        journal.setEvent(event);
        journal.setTimestamp(LocalDateTime.now().toString());
        journalRepository.save(journal);
        System.out.println("Event saved: " + event);
    }
}

