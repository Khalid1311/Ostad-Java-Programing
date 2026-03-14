package com.example.OstadAssignment15.service;

import com.example.OstadAssignment15.entity.Note;
import com.example.OstadAssignment15.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final NoteRepository noteRepository;

    public List<Note> getAllNotes() {

        return noteRepository.findAll();
    }

    public void deleteAnyNote(Long id) {
        noteRepository.deleteById(id);
    }
}
