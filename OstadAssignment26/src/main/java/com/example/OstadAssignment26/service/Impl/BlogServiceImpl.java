package com.example.OstadAssignment26.service.Impl;

import com.example.OstadAssignment26.entity.Blog;
import com.example.OstadAssignment26.entity.Contributor;
import com.example.OstadAssignment26.entity.Topic;
import com.example.OstadAssignment26.repository.BlogRepository;
import com.example.OstadAssignment26.repository.ContributorRepository;
import com.example.OstadAssignment26.repository.TopicRepository;
import com.example.OstadAssignment26.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final TopicRepository topicRepository;
    private final ContributorRepository contributorRepository;

    @Override
    public List<Contributor> getContributors() {
        return contributorRepository.findAll();
    }

    @Override
    public List<Topic> getIndices() {
        return topicRepository.findAll();
    }

    @Override
    public Blog getBlog(String topicName, String subTopicName) {

        return blogRepository
                .findByTopicNameAndSubTopicName(
                        topicName,
                        subTopicName
                )
                .orElseThrow(() ->
                        new RuntimeException("Blog not found"));

    }
}