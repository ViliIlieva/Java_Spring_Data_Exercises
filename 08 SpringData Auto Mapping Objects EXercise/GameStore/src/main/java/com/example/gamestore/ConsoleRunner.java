package com.example.gamestore;

import com.example.gamestore.entities.users.RegisterDTO;
import com.example.gamestore.entities.users.User;
import com.example.gamestore.exceprions.ValidationException;
import com.example.gamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final String REGISTER_USER_COMMAND = "RegisterUser";
    private final String LOGIN_USER_COMMAND = "LoginUser";
    private final String LOGOUT_COMMAND = "Logout";
    private final UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    private String execute(String commandLine){
        String[] commandParts = commandLine.split("\\|");
        String commandName = commandParts[0];

        return switch (commandName) {//при този вид суич, не е необходимо да слагам break
            case REGISTER_USER_COMMAND -> {
                RegisterDTO registerData = new RegisterDTO(commandParts);
                User user = userService.register(registerData);
                yield String.format("%s was registered", user.getFullName());
            }
//            case LOGIN_USER_COMMAND -> userService.login();
//            case LOGOUT_COMMAND -> userService.logout();
            default -> "unknown command";
        };
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner (System.in);

        String command = scanner.nextLine ();//чета входните данни

        String result;
        try { //проверяваме дали всичко е валидно
            result = execute(command);
        }catch (ValidationException e){
           result = e.getMessage();
        }

        System.out.println(result);
    }
}
