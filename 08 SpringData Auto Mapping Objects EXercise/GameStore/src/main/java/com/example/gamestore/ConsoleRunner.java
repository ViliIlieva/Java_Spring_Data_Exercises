package com.example.gamestore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private void execute(String commandLine){

    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner (System.in);

        String command = scanner.nextLine ();
    }
}
