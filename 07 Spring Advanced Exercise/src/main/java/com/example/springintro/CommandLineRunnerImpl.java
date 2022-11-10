package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
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
        Scanner scanner = new Scanner(System.in);
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
//        int year = Integer.parseInt(scanner.nextLine ());
//        this.bookService.findAllTitlesNotReleasedIn(year)
//                .forEach(b -> System.out.println(b.getTitle()));

        //5.	Books Released Before Date
//        String releasedDate = scanner.nextLine();
//        this.bookService.findAllTitlesBeforeReleasedDate(releasedDate)
//                .forEach (b -> System.out.printf ("%s %s %s%n", b.getTitle (),b.getEditionType(), b.getPrice ()));

        //6.	Authors Search
//        String endsString = scanner.nextLine();
//        this.authorService.findAllAuthorsWithNameEndsWith(endsString)
//                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));

        //7.	Books Search
//        String containString = scanner.nextLine();
//        this.bookService.findAllBooksByTitleContaining(containString)
//                .forEach(b -> System.out.println(b.getTitle()));

        //8.	Book Titles Search
//        String startString = scanner.nextLine();
//        this.bookService.findAllBooksByAuthorLastNameStartWith(startString)
//                .forEach(b -> System.out.println(b.getTitle() + " ("
//                        + b.getAuthor().getFirstName() + " "
//                        + b.getAuthor().getLastName() + ")"));

        //9.	Count Books
//        int longerThan = Integer.parseInt(scanner.nextLine());
//        long count = this.bookService.countBooksByTitleLongerThan(longerThan);
//        System.out.println(count);

        //10.	Total Book Copies
//        this.authorService.getWithTotalCopies()
//            .forEach(a -> System.out.println(
//                    a.getFirstName() + " " + a.getLastName() +
//                    " - " + a.getTotalCopies()));

        //11.	Reduced Book
        String title = scanner.nextLine();
        BookSummary summary = this.bookService.getInformationForTitle(title);

        System.out.printf("%s %s %s %s%n",
                summary.getTitle(),
                summary.getEditionType(),
                summary.getAgeRestriction(),
                summary.getPrice());


    }
}
