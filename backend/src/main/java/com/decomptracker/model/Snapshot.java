package com.decomptracker.model;
import jakarta.persistence.*;
import java.time.Instant;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Entity 
@Table(name = "snapshot")
public class Snapshot {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @JdbcTypeCode (SqlTypes.CHAR)
    @Column(name = "commit_sha", nullable = false, length = 40)
    private String commitSha;

    @Column (name = "committed_at", nullable = false)
    private Instant committedAt;

    @Column (name = "matched_functions", nullable = false)
    private Integer matchedFunctions;

    @Column(name = "total_functions", nullable = false)
    private Integer totalFunctions;

    protected Snapshot() {

    }

    public Snapshot(Project project, String commitSha, Instant committedAt, Integer matchedFunctions, Integer totalFunctions) {
        this.project = project;
        this.commitSha = commitSha;
        this.committedAt = committedAt;
        this.matchedFunctions = matchedFunctions;
        this.totalFunctions = totalFunctions;
    }

    public Long getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }
    public String getCommitSha() {
        return commitSha;
    }

    public Instant getCommittedAt() {
        return committedAt;
    }

    public Integer getMatchedFunctions() {
        return matchedFunctions;
    }

    public Integer getTotalFunctions() {
        return totalFunctions;
    
    }
}
