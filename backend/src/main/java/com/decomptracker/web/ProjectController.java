package com.decomptracker.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.decomptracker.model.Project;
import com.decomptracker.repository.ProjectRepository;
import com.decomptracker.repository.RepoCommitRepository;

@RestController 
@RequestMapping ("/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final RepoCommitRepository repoCommitRepository;

    public ProjectController (ProjectRepository projectRepository,
        RepoCommitRepository repoCommitRepository) {
            this.projectRepository = projectRepository;
            this.repoCommitRepository = repoCommitRepository;
        }

        public record MonthlyCommitsResponse(String month, long commits) {

        }

        @GetMapping("/{name}/commits-per-month")
        public List<MonthlyCommitsResponse> commitsPerMonth(@PathVariable String name) {
            Project project = projectRepository.findByName(name)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "project não encontrado: " + name));

            return repoCommitRepository.countCommitsPerMonth(project.getId()).stream()
                    .map(row -> new MonthlyCommitsResponse(row.getMonth(), row.getCommits()))
                    .toList();
        }
}