package com.example.journalApp.controller;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public JournalEntry getSingleEntry(@PathVariable Long myId) {
        return null;
    }

    @PostMapping
    public boolean createNewEntry(@RequestBody JournalEntry newEntry) {
        journalService.create(newEntry);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updatePreviousEntry(@PathVariable Long myId, @RequestBody JournalEntry updatedEntry) {
        return null;
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deletePreviousEntry (@PathVariable Long myId) {
        return null;
    }
}