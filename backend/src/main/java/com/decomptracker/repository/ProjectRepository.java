package com.decomptracker.repository;

import java.util.Optional;

import com.decomptracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    Optional<Project> findByName(String name);
}
