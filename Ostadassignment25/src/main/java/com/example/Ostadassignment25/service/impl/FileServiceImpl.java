package com.example.Ostadassignment25.service.impl;

import com.example.Ostadassignment25.dto.ShareResponse;
import com.example.Ostadassignment25.dto.UploadResponse;
import com.example.Ostadassignment25.entity.SharedFile;
import com.example.Ostadassignment25.exception.FileExpiredException;
import com.example.Ostadassignment25.exception.FileStorageException;
import com.example.Ostadassignment25.exception.InvalidOtpException;
import com.example.Ostadassignment25.exception.OtpAlreadyUsedException;
import com.example.Ostadassignment25.repository.SharedFileRepository;
import com.example.Ostadassignment25.service.FileService;
import com.example.Ostadassignment25.util.OtpGenerator;
import com.example.Ostadassignment25.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final SharedFileRepository repository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public UploadResponse uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new FileStorageException("File cannot be empty");
        }

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalName = file.getOriginalFilename();

            String storedName =
                    System.currentTimeMillis()
                            + "_"
                            + originalName;

            Path targetPath =
                    uploadPath.resolve(storedName);

            Files.copy(
                    file.getInputStream(),
                    targetPath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            String otp =
                    OtpGenerator.generateOtp();

            SharedFile sharedFile =
                    SharedFile.builder()
                            .fileName(originalName)
                            .filePath(targetPath.toString())
                            .otp(otp)
                            .otpUsed(false)
                            .uploadTime(LocalDateTime.now())
                            .build();

            repository.save(sharedFile);

            return UploadResponse.builder()
                    .message("File uploaded successfully")
                    .otp(otp)
                    .build();

        } catch (IOException e) {
            throw new FileStorageException(
                    "File upload failed"
            );
        }
    }

    @Override
    public ShareResponse generateDownloadLink(
            String otp
    ) {

        SharedFile file =
                repository.findByOtp(otp)
                        .orElseThrow(() ->
                                new InvalidOtpException(
                                        "Invalid OTP"
                                ));

        if (file.isOtpUsed()) {
            throw new OtpAlreadyUsedException(
                    "OTP already used"
            );
        }

        if (isExpired(file)) {
            throw new FileExpiredException(
                    "OTP expired"
            );
        }

        String token =
                TokenGenerator.generateToken();

        file.setOtpUsed(true);
        file.setDownloadToken(token);

        repository.save(file);

        String downloadLink =
                "http://localhost:8080/api/files/download/"
                        + token;

        return ShareResponse.builder()
                .message(
                        "OTP verified successfully"
                )
                .downloadLink(downloadLink)
                .build();
    }

    @Override
    public Resource downloadFile(
            String token
    ) {

        SharedFile file =
                repository
                        .findByDownloadToken(token)
                        .orElseThrow(() ->
                                new InvalidOtpException(
                                        "Invalid download link"
                                ));

        if (isExpired(file)) {
            throw new FileExpiredException(
                    "File expired"
            );
        }

        try {

            Path path =
                    Paths.get(file.getFilePath());

            Resource resource =
                    new UrlResource(path.toUri());

            if (!resource.exists()) {
                throw new FileStorageException(
                        "File not found"
                );
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new FileStorageException(
                    "Download failed"
            );
        }
    }

    private boolean isExpired(
            SharedFile file
    ) {

        return file.getUploadTime()
                .plusMinutes(10)
                .isBefore(LocalDateTime.now());
    }
}
