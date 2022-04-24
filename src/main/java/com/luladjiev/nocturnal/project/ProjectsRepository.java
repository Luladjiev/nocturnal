package com.luladjiev.nocturnal.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProjectsRepository extends JpaRepository<Project, String> {}
