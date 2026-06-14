package com.example.Ostadassignment25.scheduler;

import com.example.Ostadassignment25.entity.SharedFile;
import com.example.Ostadassignment25.repository.SharedFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileCleanupScheduler {

    private final SharedFileRepository repository;

    @Scheduled(fixedRate = 60000)
    public void deleteExpiredFiles() {

        List<SharedFile> files = repository.findAll();

        LocalDateTime now = LocalDateTime.now();

        for (SharedFile file : files) {

            if (file.getUploadTime()
                    .plusMinutes(10)
                    .isBefore(now)) {

                try {

                    Path path =
                            Paths.get(file.getFilePath());

                    Files.deleteIfExists(path);

                    repository.delete(file);

                    log.info(
                            "Deleted expired file : {}",
                            file.getFileName()
                    );

                } catch (Exception e) {

                    log.error(
                            "Delete failed : {}",
                            e.getMessage()
                    );
                }
            }
        }
    }
}