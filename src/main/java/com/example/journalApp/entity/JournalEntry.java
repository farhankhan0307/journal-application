package com.example.journalApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private @Getter @Setter ObjectId id;

    private @Getter @Setter String name;

    private @Getter @Setter String content;

    private @Getter @Setter LocalDateTime date;
}
