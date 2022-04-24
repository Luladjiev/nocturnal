package com.luladjiev.nocturnal.project;

import com.luladjiev.nocturnal.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, String> {
}
