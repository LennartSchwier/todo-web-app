package de.neuefische.todoapp.database;

import de.neuefische.todoapp.model.Note;
import de.neuefische.todoapp.model.Status;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NoteDataBase {

    private final List<Note> dataBase = new ArrayList<>(List.of(
            new Note("adab6aa2-c03d-4310-be10-6539a469bce4", "get coffee", Status.OPEN),
            new Note("a435d716-a722-43cf-a960-811d568f9461", "write backend", Status.IN_PROGRESS),
            new Note("d02c94ce-9daa-4348-8dc5-28cc6e0419b2", "take a shower", Status.DONE)
    ));

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
