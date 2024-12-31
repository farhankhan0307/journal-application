package com.example.journalApp.services;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    public void create(JournalEntry entry) {
        journalRepository.save(entry);
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }
}
