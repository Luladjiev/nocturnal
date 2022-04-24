package com.luladjiev.nocturnal.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {
  private final ProjectsRepository projectsRepository;

  public ProjectsController(ProjectsRepository projectsRepository) {
    this.projectsRepository = projectsRepository;
  }

  @GetMapping
  public List<Project> getProjects() {
    return projectsRepository.findAll();
  }
}
