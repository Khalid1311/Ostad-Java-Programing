package com.example.OstadAssignment26.repository;

import com.example.OstadAssignment26.entity.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
}