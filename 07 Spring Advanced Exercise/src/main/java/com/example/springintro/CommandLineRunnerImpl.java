package com.example.springintro;

import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();
        Scanner scanner = new Scanner (System.in);
        //1.	Books Titles by Age Restriction
//        String restriction = scanner.nextLine ();
//        this.bookService.findAllTitlesByAgeRestriction (restriction)
//                .forEach (System.out::println);

        //2.	Golden Books
//        this.bookService.findAllTitlesByEditionCopies(EditionType.GOLD, 5000)
//                .forEach (System.out::println);

        //3.	Books by Price
//        this.bookService.findAllWithPriceLessThanGreaterThan (5, 40)
//                .forEach (b -> System.out.printf ("%s - $%s%n", b.getTitle (), b.getPrice ()));

        //4.	Not Released Books
        String year = scanner.nextLine ();

    }

    private void seedData() throws IOException {
        categoryService.seedCategories ();
        authorService.seedAuthors ();
        bookService.seedBooks ();
    }
}
