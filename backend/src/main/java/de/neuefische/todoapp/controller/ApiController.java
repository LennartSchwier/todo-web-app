package de.neuefische.todoapp.controller;


import de.neuefische.todoapp.model.Note;
import de.neuefische.todoapp.model.Payload;
import de.neuefische.todoapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class ApiController {

    private NoteService noteService;

    @Autowired
    public ApiController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping
    public Note addNewNote(@RequestBody Payload payload) {
        return noteService.addNewNote(payload);
    }

    @PutMapping("{id}")
    public Note updateNote(@RequestBody Payload payload) {
        return noteService.updateNote(payload);
    }

    @DeleteMapping("{id}")
    public Note deleteNoteById(@PathVariable String id) {
        return noteService.deleteNoteById(id);
    }
}
