package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //01
    @Query("select b.title from Book b where b.ageRestriction = :ageRestriction")
    List<String> findByAgeRestriction(AgeRestriction ageRestriction);
    //02
    List<Book> findByEditionTypeAndCopiesLessThan(EditionType type, int copies);
    //03
    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);
}
