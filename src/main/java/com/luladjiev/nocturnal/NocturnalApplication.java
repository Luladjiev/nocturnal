package com.luladjiev.nocturnal;

import com.luladjiev.nocturnal.issue.Issue;
import com.luladjiev.nocturnal.project.Project;
import com.luladjiev.nocturnal.issue.IssuesRepository;
import com.luladjiev.nocturnal.project.ProjectsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class NocturnalApplication {
  public static void main(String[] args) {
    SpringApplication.run(NocturnalApplication.class, args);
  }
}
