package com.example.OstadAssignment26.scheduler;

import com.example.OstadAssignment26.service.GithubSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GithubScheduler {

    private final GithubSyncService githubSyncService;

    @Scheduled(fixedRate = 86400000)
    public void sync() {
        githubSyncService.syncRepository();
    }
}
