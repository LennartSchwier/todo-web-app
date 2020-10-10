package de.neuefische.todoapp.service;


import de.neuefische.todoapp.database.NoteDataBase;
import de.neuefische.todoapp.model.Note;
import de.neuefische.todoapp.model.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    private NoteDataBase noteDataBase;

    @Autowired
    public NoteService(NoteDataBase noteDataBase) {
        this.noteDataBase = noteDataBase;
    }

    public Note addNewNote(Payload payload) {
        Note newNote = new Note(generateUuid(), payload.getDescription(), payload.getStatus());
        return noteDataBase.addNewNote(newNote);
    }

    public List<Note> getAllNotes() {
        return noteDataBase.getAllNotes();
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
