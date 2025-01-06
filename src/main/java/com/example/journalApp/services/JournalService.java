package com.example.journalApp.services;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.User;
import com.example.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry (JournalEntry entry, String username) {
        try {
            User user = userService.getUserByUsername(username);
            entry.setDate(LocalDateTime.now());
            JournalEntry savedEntry = journalRepository.save(entry);
            user.getJournalEntries().add(savedEntry);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create new entry: " + e);
        }
    }

    public void saveEntry (JournalEntry entry) {
        journalRepository.save(entry);
    }

    public List<JournalEntry> getAll(String username) {
        User user = userService.getUserByUsername(username);
        return user.getJournalEntries();
    }

    public Optional<JournalEntry> findById (ObjectId id) {
        return journalRepository.findById(id);
    }

    @Transactional
    public void deleteById (ObjectId id, String username) {
        User user = userService.getUserByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalRepository.deleteById(id);
    }
}
