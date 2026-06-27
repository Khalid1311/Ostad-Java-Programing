package com.example.OstadAssignment26.repository;

import com.example.OstadAssignment26.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findByTopicNameAndSubTopicName(
            String topicName,
            String subTopicName
    );
}