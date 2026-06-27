package com.example.OstadAssignment26.service;

import com.example.OstadAssignment26.entity.Blog;
import com.example.OstadAssignment26.entity.Contributor;
import com.example.OstadAssignment26.entity.Topic;

import java.util.List;

public interface BlogService {

    List<Contributor> getContributors();

    List<Topic> getIndices();

    Blog getBlog(
            String topicName,
            String subTopicName
    );
}
