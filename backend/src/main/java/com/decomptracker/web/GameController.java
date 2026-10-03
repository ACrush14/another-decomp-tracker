package com.decomptracker.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.decomptracker.model.Game;
import com.decomptracker.repository.GameRepository;


@RestController 
public class GameController {
    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games")
    public List<Game> list(@RequestParam(required = false) String status) {
        if (status == null) {
            return gameRepository.findAll();
        }
        return gameRepository.findByStatus(status);
    }
}
