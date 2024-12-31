package com.example.journalApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private @Getter @Setter String id;

    private @Getter @Setter String name;

    private @Getter @Setter String content;

    private @Getter @Setter Date date;
}
