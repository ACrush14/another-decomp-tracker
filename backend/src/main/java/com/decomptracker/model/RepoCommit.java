package com.decomptracker.model;

import java.time.Instant;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "repo_commit")
public class RepoCommit {
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

    protected RepoCommit() {

    }

    public RepoCommit(Project project, String commitSha, Instant committedAt) {
        this.project = project;
        this.commitSha = commitSha;
        this.committedAt = committedAt;
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

    
}
