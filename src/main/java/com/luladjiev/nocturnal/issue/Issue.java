package com.luladjiev.nocturnal.issue;

import com.luladjiev.nocturnal.project.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
  name = "issues",
  indexes = { @Index(name = "id", columnList = "id", unique = true) }
)
@Getter
@Setter
@NoArgsConstructor
class Issue {

  @Id
  @GeneratedValue(generator = "issue-id-generator")
  @GenericGenerator(
    name = "issue-id-generator",
    strategy = "com.luladjiev.nocturnal.issue.IssueIdGenerator"
  )
  private String id;

  @NotNull
  @ManyToOne(targetEntity = Project.class)
  @JoinColumn
  private Project project;

  @NotBlank
  private String title;

  @NotBlank
  private String description;
}
