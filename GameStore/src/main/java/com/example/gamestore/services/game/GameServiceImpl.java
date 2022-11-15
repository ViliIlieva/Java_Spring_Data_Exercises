package com.example.gamestore.services.game;

import com.example.gamestore.domain.dtos.AllGamesNameAndPriceDTO;
import com.example.gamestore.domain.dtos.GameDTO;
import com.example.gamestore.domain.dtos.GameToEditDTO;
import com.example.gamestore.domain.entities.Game;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.gamestore.constants.Commands.*;
import static com.example.gamestore.constants.Validations.*;

@Service
@org.springframework.context.annotation.Configuration
public class GameServiceImpl implements GameService {
    private final ModelMapper modelMapper = new ModelMapper();
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

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    public String addGame(String[] args) {

        if (this.userService.getLoggedInUser() != null && this.userService.getLoggedInUser().isAdmin()) {
            String title = args[1];
            BigDecimal price = new BigDecimal(args[2]);
            float size = Float.parseFloat(args[3]);
            String trailer = args[4];
            String imageURL = args[5];
            String description = args[6];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate releaseDate = LocalDate.parse(args[7], formatter);

            final GameDTO gameDTO = new GameDTO(title, trailer, imageURL, size, price, description, releaseDate);
            Game gameToSave = gameDTO.toGame();
            this.gameRepository.save(gameToSave);
            return String.format(ADDED_GAME, title);
        }
        return IMPOSSIBLE_COMMAND;
    }

    @Override
    public String editGame(String[] args) {
        Long id = Long.parseLong(args[1]);
        GameToEditDTO gameToEdit = gameService.getById(id);

        if (this.userService.getLoggedInUser() != null && this.userService.getLoggedInUser().isAdmin()) {
            for (int i = 2; i < args.length; i++) {
                String[] toEdit = args[i].split("=");
                switch (toEdit[0]) {
                    case "title" -> gameToEdit.setTitle(toEdit[1]);
                    case "trailerId" -> gameToEdit.setTrailerId(toEdit[1]);
                    case "thumbnailUrl" -> gameToEdit.setThumbnailUrl(toEdit[1]);
                    case "size" -> gameToEdit.setSize(Float.parseFloat(toEdit[1]));
                    case "price" -> gameToEdit.setPrice(new BigDecimal(toEdit[1]));
                    case "description" -> gameToEdit.setDescription(toEdit[1]);
                    case "releaseDate" -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        gameToEdit.setReleaseDate(LocalDate.parse(args[1], formatter));
                    }
                    default -> throw new IllegalArgumentException("Unknown game field: " + args[0]);
                }
            }
            Game gameToSave = gameToEdit.toGame();
            this.gameRepository.save(gameToSave);
        }
        return "Edited " + gameToEdit.getTitle();
    }

    @Override
    public String deleteGame(String[] args) {
        Long id = Long.parseLong(args[1]);

        Game gameToDelete = gameService.gameGetById(id);

        if (this.userService.getLoggedInUser() != null && this.userService.getLoggedInUser().isAdmin()) {
            this.gameRepository.deleteById(id);
        }
        return "Deleted " + gameToDelete.getTitle();
    }

    @Override
    public String allGames() {
        return gameRepository.selectAllGameByTitleAndPrice()
                .stream()
                .map(AllGamesNameAndPriceDTO::info)
                .collect(Collectors.joining(System.lineSeparator()));
    }


    @Override
    public String detailGame(String[] args) {
        String title = args[1];
        Game detailGame = gameRepository.findFirstByTitle(title);

        if(detailGame == null){
            throw new IllegalArgumentException(GAME_NOT_EXIST);
        }

        return "Title " + detailGame.getTitle() + System.lineSeparator() +
                "Price: " + detailGame.getPrice() + System.lineSeparator() +
                "Description: " + detailGame.getDescription() + System.lineSeparator() +
                "Release date: " + detailGame.getReleaseDate() + System.lineSeparator();
    }

    @Override
    public GameToEditDTO getById(Long id) {
        return modelMapper.map(gameRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid game id!")),
                GameToEditDTO.class);
    }

    @Override
    public Game gameGetById(Long id) {
        return modelMapper.map(gameRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid game id!")),
                Game.class);
    }
}
