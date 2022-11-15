package com.example.gamestore.services.game;
import com.example.gamestore.domain.dtos.GameToEditDTO;
import com.example.gamestore.domain.entities.Game;;

public interface GameService {
    String addGame(String[]args);
    String editGame(String[]args);
    String deleteGame(String[]args);

    String allGames();

    String detailGame(String[]args);


    GameToEditDTO getById(Long id);

    Game gameGetById(Long id);

}
