package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override//01
    public List<String> findAllTitlesByAgeRestriction(String ageRestriction) {
        AgeRestriction restriction = AgeRestriction.valueOf (ageRestriction.toUpperCase ());
        return this.bookRepository.findByAgeRestriction (restriction);
    }

    @Override//02
    public List<String> findAllTitlesByEditionCopies(EditionType type, int copies) {
        return this.bookRepository.findByEditionTypeAndCopiesLessThan(type, copies)
                .stream ()
                .map (Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override//03
    public List<Book> findAllWithPriceLessThanGreaterThan(float lowerPrice, float higherPrice) {
        BigDecimal lower = BigDecimal.valueOf (lowerPrice);
        BigDecimal higher = BigDecimal.valueOf (higherPrice);
        return this.bookRepository.findByPriceLessThanOrPriceGreaterThan (lower, higher);
    }


    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count () > 0) {
            return;
        }

        Files
                .readAllLines (Path.of (BOOKS_FILE_PATH))
                .forEach (row -> {
                    String[] bookInfo = row.split ("\\s+");

                    Book book = createBookFromInfo (bookInfo);

                    bookRepository.save (book);
                });
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values ()[Integer.parseInt (bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse (bookInfo[1], DateTimeFormatter.ofPattern ("d/M/yyyy"));
        Integer copies = Integer.parseInt (bookInfo[2]);
        BigDecimal price = new BigDecimal (bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values ()[Integer.parseInt (bookInfo[4])];
        String title = Arrays.stream (bookInfo)
                .skip (5)
                .collect (Collectors.joining (" "));

        Author author = authorService.getRandomAuthor ();
        Set<Category> categories = categoryService
                .getRandomCategories ();

        return new Book (editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
