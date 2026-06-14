package com.example.Ostadassignment25.service;

import com.example.Ostadassignment25.dto.ShareResponse;
import com.example.Ostadassignment25.dto.UploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    UploadResponse uploadFile(MultipartFile file);

    ShareResponse generateDownloadLink(String otp);

    Resource downloadFile(String token);
}
