package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    //01
    List<String> findAllTitlesByAgeRestriction(String ageRestriction);
    //02
    List<String> findAllTitlesByEditionCopies(EditionType type, int copies);
    //03
    List<Book> findAllWithPriceLessThanGreaterThan(float lowerPrice, float higherPrice);
    //04
    List<Book> findAllTitlesNotReleasedIn(int year);
    //05
    List<Book> findAllTitlesBeforeReleasedDate(String releasedDate);
    //07
    List<Book> findAllBooksByTitleContaining(String containString);
    //08
    List<Book> findAllBooksByAuthorLastNameStartWith(String startString);
    //09
    long countBooksByTitleLongerThan(int longerThan);
    //11
    BookSummary getInformationForTitle(String title);
}
