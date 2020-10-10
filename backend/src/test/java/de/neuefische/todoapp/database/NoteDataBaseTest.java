package de.neuefische.todoapp.database;

import de.neuefische.todoapp.model.Note;
import de.neuefische.todoapp.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class NoteDataBaseTest {

    NoteDataBase testDataBase = new NoteDataBase();

    @Test
    void testAddNewNoteAddsAndReturnsCorrectObject() {
        // GIVEN
        testDataBase.addNewNote(
                new Note("adab6aa2-c03d-4310-be10-6539a469bce4", "get coffee", Status.OPEN)
        );
        testDataBase.addNewNote(
                new Note("a435d716-a722-43cf-a960-811d568f9461", "write backend", Status.IN_PROGRESS)
        );
        testDataBase.addNewNote(new Note("d02c94ce-9daa-4348-8dc5-28cc6e0419b2", "take a shower", Status.DONE)
        );

        Note testNote = new Note(
                "8eaf642d-0281-4678-9297-78581f2aef87",
                "some description",
                Status.DONE);
        // WHEN
        Note actual = testDataBase.addNewNote(testNote);

        // THEN
        assertThat(testDataBase.getAllNotes(), is(List.of(
                new Note("adab6aa2-c03d-4310-be10-6539a469bce4", "get coffee", Status.OPEN),
                new Note("a435d716-a722-43cf-a960-811d568f9461", "write backend", Status.IN_PROGRESS),
                new Note("d02c94ce-9daa-4348-8dc5-28cc6e0419b2", "take a shower", Status.DONE),
                new Note("8eaf642d-0281-4678-9297-78581f2aef87", "some description", Status.DONE)
        )));
        assertThat(actual, is(testNote));

    }

    @Test
    void testDeleteNoteByIdDeletesAndReturnsCorrectObject() {
        // GIVEN
        String id = "a435d716-a722-43cf-a960-811d568f9461";
        testDataBase.addNewNote(
                new Note("adab6aa2-c03d-4310-be10-6539a469bce4", "get coffee", Status.OPEN)
        );
        testDataBase.addNewNote(
                new Note("a435d716-a722-43cf-a960-811d568f9461", "write backend", Status.IN_PROGRESS)
        );
        testDataBase.addNewNote(new Note("d02c94ce-9daa-4348-8dc5-28cc6e0419b2", "take a shower", Status.DONE)
        );

        // WHEN
        Note actual = testDataBase.deleteNoteById(id);

        // THEN
        assertThat(testDataBase.getAllNotes(), is(List.of(
                new Note("adab6aa2-c03d-4310-be10-6539a469bce4", "get coffee", Status.OPEN),
                new Note("d02c94ce-9daa-4348-8dc5-28cc6e0419b2", "take a shower", Status.DONE)
        )));
        assertThat(actual, is(new Note("a435d716-a722-43cf-a960-811d568f9461", "write backend", Status.IN_PROGRESS)
                ));
    }

}