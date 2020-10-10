package de.neuefische.todoapp.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NoteUtils {

    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
