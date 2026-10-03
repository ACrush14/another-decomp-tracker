package com.decomptracker;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component 
@ConditionalOnProperty (name = "decomp.catalog.load-on-startup", havingValue = "true")
public class CatalogRunner implements ApplicationRunner {

    private final CatalogLoader catalogLoader;

    public CatalogRunner(CatalogLoader catalogLoader) {
        this.catalogLoader = catalogLoader;
    }

    @Override 
    public void run (ApplicationArguments args) {
        int created = catalogLoader.load();
        System.out.println("Catalogo: " + created + " jogos novos gravados");
    }
}