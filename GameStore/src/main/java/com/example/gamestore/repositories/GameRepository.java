package com.example.gamestore.repositories;

import com.example.gamestore.domain.dtos.AllGamesNameAndPriceDTO;
import com.example.gamestore.domain.dtos.GameToEditDTO;
import com.example.gamestore.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface GameRepository extends JpaRepository<Game, Long> {
    Game findFirstByTitle(String title);

    GameToEditDTO getById(long id);

    @Query("select g.title as title, g.price as price from Game as g")
    Set<AllGamesNameAndPriceDTO> selectAllGameByTitleAndPrice();

}
