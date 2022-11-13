package com.example.gamestore.services.game;

import com.example.gamestore.domain.dtos.GameDTO;
import com.example.gamestore.domain.entities.Game;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.gamestore.constants.Commands.*;
import static com.example.gamestore.constants.Validations.*;

@Service
@org.springframework.context.annotation.Configuration
public class GameServiceImpl implements GameService {
    private final ModelMapper modelMapper = new ModelMapper ();
    private final GameRepository gameRepository;
    private final UserService userService;
    private final GameService gameService;

    @Lazy
    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, GameService gameService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Bean
    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    public String addGame(String[] args) {

        if (this.userService.getLoggedInUser () != null && this.userService.getLoggedInUser ().isAdmin ()) {
            String title = args[1];
            BigDecimal price = new BigDecimal (args[2]);
            float size = Float.parseFloat (args[3]);
            String trailer = args[4];
            String imageURL = args[5];
            String description = args[6];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("dd-MM-yyyy");
            LocalDate releaseDate = LocalDate.parse (args[7], formatter);

            final GameDTO gameDTO = new GameDTO (title, trailer, imageURL, size, price, description, releaseDate);
            Game gameToSave = gameDTO.toGame ();
            this.gameRepository.save (gameToSave);
            return String.format (ADDED_GAME, title);
        }
        return IMPOSSIBLE_COMMAND;
    }

    @Override
    public String editGame(String[] args) {
        Long id = Long.parseLong (args[1]);
        GameDTO gameDTO = gameService.getById (id);

        for (int i = 2; i < args.length; i++) {
            String[] toEdit = args[i].split ("=");
            switch (toEdit[0]) {
                case "title" -> gameDTO.setTitle (toEdit[1]);
                case "trailerId" -> gameDTO.setTrailerId (toEdit[1]);
                case "thumbnailUrl" -> gameDTO.setImageUrl (toEdit[1]);
                case "size" -> gameDTO.setSize (Float.parseFloat (toEdit[1]));
                case "price" -> gameDTO.setPrice (new BigDecimal (toEdit[1]));
                case "description" -> gameDTO.setDescription (toEdit[1]);
                case "releaseDate" -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("dd-MM-yyyy");
                    gameDTO.setReleaseDate (LocalDate.parse (args[1], formatter));
                }
                default -> throw new IllegalArgumentException ("Unknown game field: " + args[0]);
            }
        }
        Game gameToSave = gameDTO.toGame ();
        this.gameRepository.save (gameToSave);
        return "Edited " + gameDTO.getTitle ();
    }

    @Override
    public String deleteGame(Long id) {
        return null;
    }

    @Override
    public GameDTO getById(Long id) {
        return modelMapper.map (gameRepository.findById (id)
                        .orElseThrow (() -> new IllegalArgumentException ("Invalid game id!")),
                GameDTO.class);
    }


}
