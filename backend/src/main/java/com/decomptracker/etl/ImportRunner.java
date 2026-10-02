package com.decomptracker.etl;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.decomptracker.model.Project;
import com.decomptracker.repository.ProjectRepository;

@Component 
@ConditionalOnProperty(name = "decomp.import.on-startup", havingValue = "true")
public class ImportRunner implements ApplicationRunner{
    private final ProjectRepository projectRepository;
    private final CommitImportService importService;

    public ImportRunner(ProjectRepository projectRepository, CommitImportService importService) {
        this.projectRepository = projectRepository;
        this.importService = importService;
    }

    @Override 
    public void run(ApplicationArguments args) {
        Project project = projectRepository.findByName("sm64")
            .orElseGet(() -> projectRepository.save( new Project("sm64", "https://github.com/n64decomp/sm64")));

            int saved = importService.importCommits(project, "n64decomp", "sm64");
            System.out.println("Importacao do sm64: " + saved + " commits novos gravados");
    }
}