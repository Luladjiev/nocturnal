package com.luladjiev.nocturnal.project;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class ProjectsService {

  private final ProjectsRepository projectsRepository;

  public ProjectsService(ProjectsRepository projectsRepository) {
    this.projectsRepository = projectsRepository;
  }

  public Project createProject(String title, String prefix) {
    return projectsRepository.saveAndFlush(new Project(prefix, title));
  }

  public Optional<Project> getProject(String id) {
    return projectsRepository.findById(id);
  }

  public Project updateProject(Project project) {
    return projectsRepository.saveAndFlush(project);
  }

  public List<Project> getProjects() {
    return projectsRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
  }

  public void deleteProject(String id) {
    projectsRepository.deleteById(id);
  }
}
