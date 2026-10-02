package com.decomptracker.etl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test 
    void buscaTodosOsCommitsDoSm64() {
        GitHubClient client = new GitHubClient();

        List<GitHubCommit> commits = client.fetchAllCommits("n64decomp", "sm64");

        assertTrue(commits.size() >= 30);
        System.out.println("Total de commits do SM64: " + commits.size());
    }
}