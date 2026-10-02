package com.decomptracker.etl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

public class GitHubClient {

    private final RestClient restClient;

    public GitHubClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader("Accept", "application/vnd.github+json")
                .defaultHeader("User-Agent", "decomp-tracker")
                .build();
    }

    public List <GitHubCommit> fetchCommitsPage(String owner, String repo, int page) {
        return restClient.get().uri("/repos/{owner}/{repo}/commits?per_page=100&page={page}", owner, repo, page)
        .retrieve()
        .body(new ParameterizedTypeReference<List<GitHubCommit>>() {});
    }
    
}
