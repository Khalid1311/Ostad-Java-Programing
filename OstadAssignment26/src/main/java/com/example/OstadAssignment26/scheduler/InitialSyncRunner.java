package com.example.OstadAssignment26.scheduler;

import com.example.OstadAssignment26.service.GithubSyncService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialSyncRunner {

    private final GithubSyncService githubSyncService;

    @PostConstruct
    public void init() {
        githubSyncService.syncRepository();
    }
}
