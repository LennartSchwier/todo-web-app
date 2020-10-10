package de.neuefische.todoapp.database;

import de.neuefische.todoapp.model.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NoteDataBase {

    private final List<Note> dataBase = new ArrayList<>();

    public List<Note> getAllNotes() {
        return dataBase;
    }

    public Note addNewNote(Note newNote) {
        dataBase.add(newNote);
        return newNote;
    }

    public Note deleteNoteById(String id) {
        for (Note searchedNote: dataBase) {
            if(searchedNote.getId().equals(id)) {
                dataBase.remove(searchedNote);
                return searchedNote;
            }
        }
        return null;
    }
}
