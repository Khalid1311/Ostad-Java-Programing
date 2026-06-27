package com.example.OstadAssignment26.service.Impl;

import com.example.OstadAssignment26.entity.Blog;
import com.example.OstadAssignment26.entity.Contributor;
import com.example.OstadAssignment26.entity.Topic;
import com.example.OstadAssignment26.repository.BlogRepository;
import com.example.OstadAssignment26.repository.ContributorRepository;
import com.example.OstadAssignment26.repository.TopicRepository;
import com.example.OstadAssignment26.service.GithubSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubSyncServiceImpl implements GithubSyncService {

    private final RestTemplate restTemplate;
    private final BlogRepository blogRepository;
    private final TopicRepository topicRepository;
    private final ContributorRepository contributorRepository;

    @Value("${github.root.readme}")
    private String rootReadmeUrl;

    @Override
    public void syncRepository() {

        String readme =
                restTemplate.getForObject(
                        rootReadmeUrl,
                        String.class
                );

        topicRepository.deleteAll();

        String[] topics = {
                "datatype",
                "operator",
                "classesandobject",
                "theobjectclass",
                "wrapperclass",
                "exceptionhandling",
                "assertion",
                "string",
                "datetime",
                "formatter",
                "regex",
                "array",
                "inheritance",
                "interfaces",
                "enum",
                "java17",
                "qna"
        };

        for (int i = 0; i < topics.length; i++) {

            Topic topic = Topic.builder()
                    .topicName(topics[i])
                    .topicOrder(i + 1)
                    .build();

            topicRepository.save(topic);
        }

        contributorRepository.deleteAll();

        Contributor contributor = Contributor.builder()
                .githubUsername("abusaeed2433")
                .displayName("Abu Saeed")
                .build();

        contributorRepository.save(contributor);
    }

    @Override
    public Blog fetchAndSaveBlog(
            String topicName,
            String subTopicName
    ) {

        Blog existing =
                blogRepository
                        .findByTopicNameAndSubTopicName(
                                topicName,
                                subTopicName
                        )
                        .orElse(null);

        if (existing != null) {
            return existing;
        }

        String url =
                "https://raw.githubusercontent.com/abusaeed2433/JavaInREADME/main/"
                        + topicName
                        + "/"
                        + subTopicName
                        + "/README.md";

        System.out.println("URL: " + url);

        String content = restTemplate.getForObject(url, String.class);

        if (content == null || content.isBlank()) {
            throw new RuntimeException("README not found");
        }

        String title = content.lines()
                .filter(line -> !line.trim().isEmpty())
                .findFirst()
                .orElse(topicName);

        Blog blog = Blog.builder()
                .topicName(topicName)
                .subTopicName(subTopicName)
                .title(title)
                .content(content)
                .build();

        return blogRepository.save(blog);
    }
}