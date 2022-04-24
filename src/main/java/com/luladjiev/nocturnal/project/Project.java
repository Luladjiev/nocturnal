package com.luladjiev.nocturnal.project;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
    name = "projects",
    indexes = {@Index(name = "id", columnList = "id", unique = true)})
@Getter
@Setter
@NoArgsConstructor
public class Project {
  @Id
  @Column(nullable = false, updatable = false, length = 6)
  private String id;

  @NotBlank private String title;
}
