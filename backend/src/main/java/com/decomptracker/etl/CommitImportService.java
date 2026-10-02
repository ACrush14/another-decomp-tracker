package com.decomptracker.etl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decomptracker.model.Project;
import com.decomptracker.model.RepoCommit;
import com.decomptracker.repository.RepoCommitRepository;
@Service 
public class CommitImportService {
    private final GitHubClient gitHubClient;
    private final RepoCommitRepository repoCommitRepository;

    public CommitImportService(GitHubClient gitHubClient, RepoCommitRepository repoCommitRepository) {
        this.gitHubClient = gitHubClient;
        this.repoCommitRepository = repoCommitRepository;
    }

    @Transactional
    public int importCommits(Project project, String owner, String repo) {
        List<GitHubCommit> commits = gitHubClient.fetchAllCommits(owner, repo);

        int saved = 0;
        for (GitHubCommit commit : commits) {
            boolean alreadyExists = repoCommitRepository.existsByProjectAndCommitSha(project, commit.sha());

            if (!alreadyExists) {
                repoCommitRepository.save(new RepoCommit(project, commit.sha(), commit.committedAt()));
                saved++;
            }
        }

        return saved;
    }
    
}
