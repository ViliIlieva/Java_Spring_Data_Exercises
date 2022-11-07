package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;
    //01
    List<String> findAllTitlesByAgeRestriction(String ageRestriction);
    //02
    List<String> findAllTitlesByEditionCopies(EditionType type, int copies);
    //03
    List<Book> findAllWithPriceLessThanGreaterThan(float lowerPrice, float higherPrice);
}
