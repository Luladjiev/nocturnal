package com.luladjiev.nocturnal.project;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
class ProjectsController {
  private final ProjectsService projectsService;

  public ProjectsController(ProjectsService projectsService) {

    this.projectsService = projectsService;
  }

  @GetMapping
  public List<Project> getProjects() {
    return projectsService.getProjects();
  }

  @GetMapping("/{id}")
  public Project getProject(@PathVariable String id) {
    return projectsService.getProject(id).orElseThrow();
  }

  @PostMapping
  public Project createProject(@RequestBody Project project) {
    return projectsService.createProject(project.getTitle(), project.getId());
  }
}
