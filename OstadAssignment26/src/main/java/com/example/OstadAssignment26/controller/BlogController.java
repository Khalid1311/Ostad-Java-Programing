package com.example.OstadAssignment26.controller;

import com.example.OstadAssignment26.entity.Blog;
import com.example.OstadAssignment26.entity.Contributor;
import com.example.OstadAssignment26.entity.Topic;
import com.example.OstadAssignment26.service.BlogService;
import com.example.OstadAssignment26.service.GithubSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final GithubSyncService githubSyncService;

    @GetMapping("/read_contributions")
    public List<Contributor> getContributors() {
        return blogService.getContributors();
    }

    @GetMapping("/read_indices")
    public List<Topic> getIndices() {
        return blogService.getIndices();
    }

    @GetMapping("/read_blog")
    public Blog getBlog(
            @RequestParam String topic_name,
            @RequestParam String sub_topic_name
    ) {

        return githubSyncService.fetchAndSaveBlog(
                topic_name,
                sub_topic_name
        );
    }


}
