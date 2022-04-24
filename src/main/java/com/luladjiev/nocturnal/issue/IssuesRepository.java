package com.luladjiev.nocturnal.issue;

import com.luladjiev.nocturnal.issue.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesRepository extends JpaRepository<Issue, Long> {
}
