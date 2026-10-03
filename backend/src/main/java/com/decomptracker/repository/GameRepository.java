package com.decomptracker.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decomptracker.model.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

    Optional<Game> findByTitle(String title);
    List<Game> findByStatus(String status);
    
}
