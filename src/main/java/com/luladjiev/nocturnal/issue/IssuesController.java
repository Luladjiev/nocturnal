package com.luladjiev.nocturnal.issue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
class IssuesController {

  private final IssuesRepository issuesRepository;

  public IssuesController(IssuesRepository issuesRepository) {
    this.issuesRepository = issuesRepository;
  }

  @GetMapping
  public List<Issue> getIssues() {
    return issuesRepository.findAll();
  }
}
