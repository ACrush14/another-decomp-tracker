package com.decomptracker.repository;

import com.decomptracker.model.Project;
import com.decomptracker.model.RepoCommit;
import org.springframework.data.jpa.repository.JpaRepository;
import com.decomptracker.model.Project;

public interface RepoCommitRepository extends JpaRepository <RepoCommit, Long> {
    boolean existsByProjectAndCommitSha(Project project, String commitSha);
}