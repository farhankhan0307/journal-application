package com.example.journalApp.services;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.User;
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

    @Autowired
    private UserService userService;

    public void saveEntry (JournalEntry entry, String username) {
        User user = userService.getUserByUsername(username);
        if(user != null) {
            entry.setDate(LocalDateTime.now());
            JournalEntry savedEntry = journalRepository.save(entry);
            user.getJournalEntries().add(savedEntry);
            userService.saveUser(user);
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

    public void deleteById (ObjectId id, String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.saveUser(user);
            journalRepository.deleteById(id);
        }
    }
}
