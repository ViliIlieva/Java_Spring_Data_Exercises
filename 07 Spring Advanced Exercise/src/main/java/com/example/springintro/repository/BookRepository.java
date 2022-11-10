package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    //04
    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);
    //05
    List<Book> findByReleaseDateBefore(LocalDate before);
    //07
    List<Book> findByTitleContaining(String containString);
    //08
    List<Book> findByAuthorLastNameStartingWith(String search);
    //09
    @Query("select count(b.id) from Book as b where length(b.title) > :longerThan")
    long countByTitleLongerThan(int longerThan);
    //11
    @Query("select b.title as title, b.editionType as editionType," +
            " b.ageRestriction as ageRestriction, b.price as price" +
            " from Book b" +
            " where b.title = :title")
    BookSummary findSummaryForTitle(String title);


}
