package com.example.gamestore;

import com.example.gamestore.services.game.GameService;
import com.example.gamestore.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.gamestore.constants.Commands.*;

@Component
public class ConsoleRunner implements CommandLineRunner {
    ModelMapper modelMapper = new ModelMapper ();
    Scanner scanner = new Scanner (System.in);

    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        String input = scanner.nextLine();

        while (! input.equals ("close")){
            String[] arguments = input.split ("\\|");
            String command = arguments[0];

            String output = switch (command) {
                case REGISTER_USER -> userService.registerUser (arguments);
                case LOGIN_USER -> userService.loginUser (arguments);
                case LOGOUT -> userService.logoutUser();
                case ADD_GAME -> gameService.addGame (arguments);
                case EDIT_GAME -> gameService.editGame (arguments);
                case DELETE_GAME -> gameService.deleteGame(arguments);
                case ALL_GAME -> gameService.allGames();
                case DETAIL_GAME -> gameService.detailGame(arguments);
                case OWNED_GAME -> userService.ownedGames();
                default  -> COMMAND_NOT_FOUND_MESSAGE;
            };

            System.out.println (output);
            input = scanner.nextLine ();
        }


    }
}
