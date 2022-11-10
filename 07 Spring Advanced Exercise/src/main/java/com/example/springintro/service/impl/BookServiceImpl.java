package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Override//04
    public List<Book> findAllTitlesNotReleasedIn(int year) {
        LocalDate before = LocalDate.of(year, 1,1);
        LocalDate after = LocalDate.of(year, 12,31);
        return this.bookRepository.findByReleaseDateBeforeOrReleaseDateAfter(before, after);
    }

    @Override//05
    public List<Book> findAllTitlesBeforeReleasedDate(String releasedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate before = LocalDate.parse(releasedDate, formatter);
        return this.bookRepository.findByReleaseDateBefore(before);
    }

    @Override//08
    public List<Book> findAllBooksByTitleContaining(String containString) {
        String lowerCase = containString.toLowerCase();
        return this.bookRepository.findByTitleContaining(containString);
    }

    @Override//08
    public List<Book> findAllBooksByAuthorLastNameStartWith(String startString) {
        return this.bookRepository.findByAuthorLastNameStartingWith(startString);
    }

    @Override//09
    public long countBooksByTitleLongerThan(int longerThan) {
        return this.bookRepository.countByTitleLongerThan(longerThan);
    }

    @Override//11
    public BookSummary getInformationForTitle(String title) {
        return this.bookRepository.findSummaryForTitle(title);
    }


}
