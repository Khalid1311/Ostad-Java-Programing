package com.example.OstadAssignment15.controller;

import com.example.OstadAssignment15.entity.Note;
import com.example.OstadAssignment15.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        return ResponseEntity.ok(noteService.createNote(note,getCurrentUsername()));
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNote(Authentication auth){
        return ResponseEntity.ok(noteService.getAllNote(auth));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id, Authentication auth){
        return ResponseEntity.ok(noteService.getNoteById(id,auth));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNoteById(@PathVariable Long id, Authentication auth){
        return ResponseEntity.ok(noteService.deleteNoteById(id,auth));
    }
}
