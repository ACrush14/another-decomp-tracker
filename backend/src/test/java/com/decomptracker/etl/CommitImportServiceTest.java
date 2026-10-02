package com.decomptracker.etl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.decomptracker.model.Project;
import com.decomptracker.repository.ProjectRepository;
import com.decomptracker.repository.RepoCommitRepository;

@DataJpaTest 
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(CommitImportService.class)

class CommitImportServiceTest {
    @Autowired 
    private CommitImportService service;

    @Autowired 
    private ProjectRepository projectRepository;

    @Autowired 
    private RepoCommitRepository repoCommitRepository;

    @MockitoBean
    private GitHubClient gitHubClient;

    @Test 
    void importarDuasVezesNaoDuplicaCommits() {
        Project project = projectRepository.save(new Project("project-import", "https://github.com/exemplo/import"));

        when(gitHubClient.fetchAllCommits("dono", "repo")).thenReturn(List.of(fakeCommit("a".repeat(40)),
                fakeCommit("b".repeat(40)),
                fakeCommit("c".repeat(40))));

    long antes = repoCommitRepository.count();

    int primeira = service.importCommits(project, "dono", "repo");
    int segunda = service.importCommits(project, "dono", "repo");

    assertEquals(3, primeira);
    assertEquals(0, segunda);
    assertEquals(antes + 3, repoCommitRepository.count());
    }
    private GitHubCommit fakeCommit(String sha) {
        return new GitHubCommit(sha, new GitHubCommit.Detail(new GitHubCommit.Person(Instant.now())));
    }


    
}
