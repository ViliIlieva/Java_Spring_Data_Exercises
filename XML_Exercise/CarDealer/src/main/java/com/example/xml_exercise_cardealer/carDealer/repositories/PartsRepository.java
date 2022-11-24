package com.example.xml_exercise_cardealer.carDealer.repositories;

import com.example.xml_exercise_cardealer.carDealer.entities.parts.Parts;
import com.example.xml_exercise_cardealer.carDealer.entities.parts.PartsNameAndPriceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartsRepository extends JpaRepository<Parts, Long> {
    @Query("select c.parts from Car c join c.parts p")
    List<Parts> findPartsByCarId(long id);
}
