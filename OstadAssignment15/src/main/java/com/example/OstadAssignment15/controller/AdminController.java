package com.example.OstadAssignment15.controller;

import com.example.OstadAssignment15.entity.Note;
import com.example.OstadAssignment15.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(){
        return ResponseEntity.ok(adminService.getAllNotes());
    }

    @DeleteMapping("/notes/{id}")
    public void deleteAnyNote(@PathVariable Long id){
        adminService.deleteAnyNote(id);
    }

}
