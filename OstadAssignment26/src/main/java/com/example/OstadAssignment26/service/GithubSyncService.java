package com.example.OstadAssignment26.service;

import com.example.OstadAssignment26.entity.Blog;

public interface GithubSyncService {

    void syncRepository();

    Blog fetchAndSaveBlog(String topicName, String subTopicName);
}
