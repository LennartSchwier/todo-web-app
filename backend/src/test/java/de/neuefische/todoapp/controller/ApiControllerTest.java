package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.model.Note;
import de.neuefische.todoapp.model.Payload;
import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.utils.NoteUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    NoteUtils utilsMock;

    @Test
    void testPostMappingIntegration() {
        // GIVEN
        String url = "http://localhost:" + port + "api/todo";
        Note expectedResponse = new Note(
                "288e002a-4f06-464e-8f77-09103edb7eb2",
                "some description",
                Status.IN_PROGRESS
        );
        Payload payload = new Payload(
                "some description",
                "288e002a-4f06-464e-8f77-09103edb7eb2",
                Status.IN_PROGRESS
        );
        when(utilsMock.generateUuid())
                .thenReturn("288e002a-4f06-464e-8f77-09103edb7eb2");

        // WHEN
        ResponseEntity<Note> response = restTemplate.postForEntity(
                url,
                payload,
                Note.class
        );

        // THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(expectedResponse));
    }

    @Test
    void testGetMappingIntegration() {
        // GIVEN
        String url = "http://localhost:" + port + "api/todo";
        List<Note> expectedResponse = new ArrayList<>(List.of(
                new Note(
                        "288e002a-4f06-464e-8f77-09103edb7eb2",
                        "some description",
                        Status.IN_PROGRESS
        )));

        // WHEN
        ResponseEntity<Note[]> response =
                restTemplate.getForEntity(url, Note[].class);

        // THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(expectedResponse.toArray()));
    }
}