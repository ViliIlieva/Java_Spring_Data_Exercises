package com.example.gamestore.repositories;

import com.example.gamestore.domain.dtos.GameDTO;
import com.example.gamestore.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<Game, Long> {
    Game findFirstByTitle(String title);

    GameDTO getById(long id);
}
