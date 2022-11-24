package com.example.xml_exercise_cardealer.carDealer.repositories;

import com.example.xml_exercise_cardealer.carDealer.entities.sales.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
