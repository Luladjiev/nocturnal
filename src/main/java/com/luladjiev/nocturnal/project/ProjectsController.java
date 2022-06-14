package com.luladjiev.nocturnal.project;

import com.luladjiev.nocturnal.common.NotFoundException;
import java.util.List;
import org.springframework.web.bind.annotation.*;

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
    return projectsService
      .getProject(id)
      .orElseThrow(() ->
        new NotFoundException(String.format("Project with ID %s not found", id))
      );
  }

  @PostMapping
  public Project createProject(@RequestBody Project project) {
    return projectsService.createProject(project.getTitle(), project.getId());
  }

  @PutMapping("/{id}")
  public Project updateProject(
    @PathVariable String id,
    @RequestBody Project project
  ) {
    return projectsService
      .getProject(id)
      .map(proj -> projectsService.updateProject(project))
      .orElseThrow(() ->
        new NotFoundException(String.format("Project with ID %s not found", id))
      );
  }

  @DeleteMapping("/{id}")
  public void deleteProject(@PathVariable String id) {
    projectsService.deleteProject(id);
  }
}
