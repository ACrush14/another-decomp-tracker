package com.decomptracker.etl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.hibernate.annotations.Changelog.Timestamp;
import java.util.List;
import org.junit.jupiter.api.Test;

class GitHubClientTest{
    @Test
    void buscaCommitsDoSm64() {
        GitHubClient client = new GitHubClient();

        List<GitHubCommit> commits = client.fetchCommitsPage("n64decomp", "sm64", 1);

        assertFalse(commits.isEmpty());
        assertNotNull(commits.get(0).sha());
        assertNotNull(commits.get(0).committedAt());
        System.out.println("Commits recebidos: " + commits.size());
    }
}