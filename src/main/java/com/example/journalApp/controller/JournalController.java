package com.example.journalApp.controller;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.services.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    private JournalService journalService;

    @GetMapping
    public List<JournalEntry> getAllEntries () {
        return journalService.getAll();
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getSingleEntry(@PathVariable ObjectId myId) {
        return journalService.findById(myId).orElse(null);
    }

    @PostMapping
    public JournalEntry createNewEntry(@RequestBody JournalEntry newEntry) {
        newEntry.setDate(LocalDateTime.now());
        journalService.saveEntry(newEntry);
        return newEntry;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updatePreviousEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry updatedEntry) {
        JournalEntry oldEntry = journalService.findById(myId).orElse(null);
        if (oldEntry != null) {
            oldEntry.setName(updatedEntry.getName() != null && !updatedEntry.getName().equals("") ? updatedEntry.getName() : oldEntry.getName());
            oldEntry.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals("") ? updatedEntry.getContent() : oldEntry.getContent());
        }
        journalService.saveEntry(oldEntry);
        return oldEntry;
    }

    @DeleteMapping("/id/{myId}")
    public boolean deletePreviousEntry (@PathVariable ObjectId myId) {
        journalService.deleteById(myId);
        return true;
    }
}