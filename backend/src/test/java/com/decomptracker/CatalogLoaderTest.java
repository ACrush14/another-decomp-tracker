package com.decomptracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.decomptracker.repository.GameRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(CatalogLoader.class)
class CatalogLoaderTest {

    @Autowired
    private CatalogLoader loader;

    @Autowired
    private GameRepository gameRepository;

    @Test 
    void carregarDuasVezesNaoDuplicaJogos() {
        gameRepository.deleteAll();

        int primeira = loader.load();
        int segunda = loader.load();

        assertTrue(primeira > 0);
        assertEquals(0, segunda);
        assertEquals(primeira, gameRepository.count());
    }

}
