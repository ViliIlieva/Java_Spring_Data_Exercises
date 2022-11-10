package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNamesWithTotalCopies;
import java.util.List;

public interface AuthorService {
    //06
    List<Author> findAllAuthorsWithNameEndsWith(String endsString);
    //10 правим си един поджо клас в ентититата
    List<AuthorNamesWithTotalCopies> getWithTotalCopies();
}
