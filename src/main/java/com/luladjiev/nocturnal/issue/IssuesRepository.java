package com.luladjiev.nocturnal.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IssuesRepository extends JpaRepository<Issue, Long> {}
