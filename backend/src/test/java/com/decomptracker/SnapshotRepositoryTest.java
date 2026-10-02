package com.decomptracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import java.time.Instant;
import com.decomptracker.model.Project;

import com.decomptracker.model.Snapshot;
import com.decomptracker.repository.ProjectRepository;
import com.decomptracker.repository.SnapshotRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SnapshotRepositoryTest {
    @Autowired 
    private ProjectRepository projectRepository;

    @Autowired 
    private SnapshotRepository snapshotRepository;
    

    @Test  
    void salvarELeSnapshot() {
        Project project = projectRepository.save (
            new Project("projeto-teste", "https://github.com/Kirby64Ret/kirby64"));

        Snapshot snapshot = snapshotRepository.save(
            new Snapshot(project, "a".repeat(40), Instant.now(), 10, 100));

        Snapshot lido = snapshotRepository.findById(snapshot.getId()).get();

        assertEquals(snapshot.getCommitSha(), lido.getCommitSha());
        assertEquals(10, lido.getMatchedFunctions());
        assertEquals(100, lido.getTotalFunctions());
    }
}