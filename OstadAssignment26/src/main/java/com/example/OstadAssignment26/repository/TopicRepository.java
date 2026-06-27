package com.example.OstadAssignment26.repository;

import com.example.OstadAssignment26.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}