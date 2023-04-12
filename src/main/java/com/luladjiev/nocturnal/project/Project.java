package com.luladjiev.nocturnal.project;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = "projects",
  indexes = { @Index(name = "id", columnList = "id", unique = true) }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

  @Id
  @Column(nullable = false, updatable = false, length = 6)
  private String id;

  @NotBlank
  private String title;
}
