package com.decomptracker.repository;

import com.decomptracker.model.Project;
import com.decomptracker.model.RepoCommit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepoCommitRepository extends JpaRepository <RepoCommit, Long> {
    boolean existsByProjectAndCommitSha(Project project, String commitSha);

    @Query(value = """
        SELECT to_char(date_trunc('month', committed_at AT TIME ZONE 'UTC'), 'YYYY-MM') AS month,
               count(*) AS commits
        FROM repo_commit
        WHERE project_id = :projectId
        GROUP BY 1
        ORDER BY 1
        """, nativeQuery = true)
List<MonthlyCommits> countCommitsPerMonth(@Param("projectId") Long projectId);
}