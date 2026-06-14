package com.example.Ostadassignment25.repository;


import com.example.Ostadassignment25.entity.SharedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SharedFileRepository extends JpaRepository<SharedFile, Long> {

    Optional<SharedFile> findByOtp(String otp);

    Optional<SharedFile> findByDownloadToken(String token);

    List<SharedFile>
    findByUploadTimeBefore(
            LocalDateTime dateTime
    );
}
