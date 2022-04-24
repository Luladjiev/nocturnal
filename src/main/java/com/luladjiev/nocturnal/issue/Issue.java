package com.luladjiev.nocturnal.issue;

import com.luladjiev.nocturnal.project.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(
    name = "issues",
    indexes = {@Index(name = "id", columnList = "id", unique = true)})
@Getter
@Setter
@NoArgsConstructor
class Issue {
  @Id
  @GeneratedValue(generator = "issue-id-generator")
  @GenericGenerator(
      name = "issue-id-generator",
      strategy = "com.luladjiev.nocturnal.issue.IssueIdGenerator")
  private String id;

  @NotNull
  @ManyToOne(targetEntity = Project.class)
  @JoinColumn
  private Project project;

  @NotBlank private String title;

  @NotBlank private String description;
}
