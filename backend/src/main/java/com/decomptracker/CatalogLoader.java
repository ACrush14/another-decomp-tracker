package com.decomptracker;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decomptracker.model.Game;
import com.decomptracker.repository.GameRepository;


@Service 
public class CatalogLoader {
    private static final String CSV_PATH = "catalog/n64-decomp-progress.csv";

    private final GameRepository gameRepository;

    public CatalogLoader(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional 
    public int load() {
        int created = 0;

        try (Reader reader = new InputStreamReader(new ClassPathResource(CSV_PATH).getInputStream(),
    StandardCharsets.UTF_8);
CSVParser parser = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).setTrim(true).get().parse(reader)) {

    for (CSVRecord row : parser) {
        String title = row.get("title");
        String repoUrl = text (row, "repo_url");
        String status = row.get("status");
        BigDecimal percent = decimal (row, "progress_percent");
        String metric = row.get("progress_metric");
        Integer matched = integer(row, "matched_functions");
        Integer total = integer(row, "total_functions");
        String note = text(row, "progress_note");
        String sourceUrl = text(row, "source_url");
        LocalDate checkedOn = date(row, "checked_on");

        Game existing = gameRepository.findByTitle(title).orElse(null);
        if (existing == null) {
            gameRepository.save(new Game(title, repoUrl, status, percent, metric, matched, total, note, sourceUrl, checkedOn));
            created++;
        } else {
            existing.update(repoUrl, status, percent, metric, matched, total, note, sourceUrl, checkedOn);
        }
    }
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return created;
    }
    private static String text(CSVRecord row, String column) {
        String value = row.get(column);
        return value == null || value.isBlank() ? null : value;
    }

    private static BigDecimal decimal (CSVRecord row, String column) {
        String value = text(row, column);
        return value == null ? null : new BigDecimal(value);
    }

    private static Integer integer(CSVRecord row, String column) {
        String value = text(row, column);
        return value == null ? null : Integer.valueOf(value);
    }

    private static LocalDate date(CSVRecord row, String column) {
        String value = text(row, column);
        return value == null ? null : LocalDate.parse(value);
    }
}
