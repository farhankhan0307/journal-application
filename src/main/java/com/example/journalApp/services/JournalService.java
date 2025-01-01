package com.example.journalApp.services;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    public void saveEntry (JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        journalRepository.save(entry);
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> findById (ObjectId id) {
        return journalRepository.findById(id);
    }

    public void deleteById (ObjectId id) {
        journalRepository.deleteById(id);
    }
}
