package com.decomptracker.repository;

import com.decomptracker.model.RepoCommit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoCommitRepository extends JpaRepository <RepoCommit, Long> {

}