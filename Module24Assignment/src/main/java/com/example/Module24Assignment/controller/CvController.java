package com.example.Module24Assignment.controller;

import com.example.Module24Assignment.dto.EvaluationResponse;
import com.example.Module24Assignment.service.CvEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/cv")
@RequiredArgsConstructor
public class CvController {

    private final CvEvaluationService cvEvaluationService;

    @PostMapping("/evaluate")
    public ResponseEntity<EvaluationResponse> evaluateCV(
            @RequestParam("file") MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");        }

        return ResponseEntity.ok(cvEvaluationService.evaluate(file));
    }
}
