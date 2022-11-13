package com.example.gamestore.services.game;

import com.example.gamestore.domain.dtos.GameDTO;

public interface GameService {
    String addGame(String[]args);
    String editGame(String[]args);
    String deleteGame(Long id);

    GameDTO getById(Long id);

}
