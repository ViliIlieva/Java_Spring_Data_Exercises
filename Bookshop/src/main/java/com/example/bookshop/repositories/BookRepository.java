package com.example.bookshop.repositories;

import com.example.bookshop.domain.entities.Book;
import com.example.bookshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
