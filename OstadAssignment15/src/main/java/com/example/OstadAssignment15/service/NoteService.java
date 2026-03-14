package com.example.OstadAssignment15.service;

import com.example.OstadAssignment15.entity.Note;
import com.example.OstadAssignment15.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note createNote(Note note,String userName) {
        note.setOwnerUsername(userName);
        return noteRepository.save(note);
    }

    public List<Note> getAllNote(Authentication auth) {
        return noteRepository.findByOwnerUsername(auth.getName());
    }

    public Note getNoteById(Long id, Authentication auth) {
        Note note = noteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Note not found"));

        if(!note.getOwnerUsername().equals(auth.getName())){
            throw new RuntimeException("Access Denied");
        }
        return note;
    }

    public String deleteNoteById(Long id, Authentication auth) {
        Note note = noteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Note not found"));

        if(!note.getOwnerUsername().equals(auth.getName())){
            throw new RuntimeException("Access Denied");
        }

        noteRepository.delete(note);
        return "Note Delete";
    }
}
