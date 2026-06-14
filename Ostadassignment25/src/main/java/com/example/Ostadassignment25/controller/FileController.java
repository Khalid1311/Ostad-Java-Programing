package com.example.Ostadassignment25.controller;

import com.example.Ostadassignment25.dto.ShareResponse;
import com.example.Ostadassignment25.dto.UploadResponse;
import com.example.Ostadassignment25.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {

        UploadResponse response =
                fileService.uploadFile(file);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/share")
    public ResponseEntity<ShareResponse> getDownloadLink(
            @RequestParam String otp
    ) {

        ShareResponse response =
                fileService.generateDownloadLink(otp);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{token}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String token
    ) {

        Resource resource =
                fileService.downloadFile(token);

        return ResponseEntity.ok()
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + resource.getFilename()
                                + "\""
                )
                .body(resource);
    }
}
