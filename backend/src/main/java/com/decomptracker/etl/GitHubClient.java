package com.decomptracker.etl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
@Component
public class GitHubClient {

    private static final int PAGE_SIZE = 100;

    private final RestClient restClient;

    public GitHubClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader("Accept", "application/vnd.github+json")
                .defaultHeader("User-Agent", "decomp-tracker")
                .build();
    }

    public List <GitHubCommit> fetchCommitsPage(String owner, String repo, int page) {
        return restClient.get().uri("/repos/{owner}/{repo}/commits?per_page={perPage}&page={page}", owner, repo, PAGE_SIZE, page)
        .retrieve()
        .body(new ParameterizedTypeReference<List<GitHubCommit>>() {});
    }
    

    public List<GitHubCommit> fetchAllCommits(String owner, String repo) {
        List<GitHubCommit> all = new ArrayList<>();
        int page = 1;

        while (true) {
            List<GitHubCommit> batch = fetchCommitsPage(owner, repo, page);
            all.addAll(batch);

            if (batch.size() < PAGE_SIZE) {
                break;
            }
            page++;
        }
        return all;
    }


}
