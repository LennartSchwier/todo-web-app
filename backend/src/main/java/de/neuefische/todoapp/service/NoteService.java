package de.neuefische.todoapp.service;


import de.neuefische.todoapp.database.NoteDataBase;
import de.neuefische.todoapp.model.Note;
import de.neuefische.todoapp.model.Payload;
import de.neuefische.todoapp.utils.NoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteDataBase noteDataBase;
    private NoteUtils noteUtils;

    @Autowired
    public NoteService(NoteDataBase noteDataBase, NoteUtils noteUtils) {
        this.noteDataBase = noteDataBase;
        this.noteUtils = noteUtils;
    }

    public Note addNewNote(Payload payload) {
        Note newNote = new Note(noteUtils.generateUuid(), payload.getDescription(), payload.getStatus());
        return noteDataBase.addNewNote(newNote);
    }

    public List<Note> getAllNotes() {
        return noteDataBase.getAllNotes();
    }

    public Note updateNote(Payload payload) {
        Note updatedNote = new Note(payload.getId(), payload.getDescription(), payload.getStatus());
        noteDataBase.deleteNoteById(payload.getId());
        noteDataBase.addNewNote(updatedNote);
        return updatedNote;
    }

    public Note deleteNoteById(String id) {
        return noteDataBase.deleteNoteById(id);
    }
}
