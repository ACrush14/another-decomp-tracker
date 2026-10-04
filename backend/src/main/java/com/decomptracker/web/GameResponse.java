package com.decomptracker.web;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.decomptracker.model.Game;

public record GameResponse (
    Long id,
    String title,
    String repoUrl,
    String status,
    BigDecimal progressPercent,
    String progressMetric,
    Integer matchedFunctions,
    Integer totalFunctions,
    String progressNote,
    String sourceUrl,
    LocalDate checkedOn) {

        public static GameResponse from(Game game) {
            return new GameResponse (
                game.getId(),
                game.getTitle(),
                game.getRepoUrl(),
                game.getStatus(),
                game.getProgressPercent(),
                game.getProgressMetric(),
                game.getMatchedFunctions(),
                game.getTotalFunctions(),
                game.getProgressNote(),
                game.getSourceUrl(),
                game.getCheckedOn());
    }
}
