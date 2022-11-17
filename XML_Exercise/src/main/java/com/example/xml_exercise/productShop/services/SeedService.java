package com.example.xml_exercise.productShop.services;

import java.io.FileNotFoundException;

public interface SeedService {
    void seedUsers() throws FileNotFoundException;
    void seedCategories() throws FileNotFoundException;
    void seedProducts() throws FileNotFoundException;

    default void seedAll() throws FileNotFoundException {
        seedUsers();
        seedCategories ();
        seedProducts ();
    }
}
