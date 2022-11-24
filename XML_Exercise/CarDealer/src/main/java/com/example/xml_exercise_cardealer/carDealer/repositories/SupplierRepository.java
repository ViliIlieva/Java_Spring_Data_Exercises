package com.example.xml_exercise_cardealer.carDealer.repositories;

import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.ExportLocalSupplierDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT new com.example.xml_exercise_cardealer.carDealer.entities.suppliers.ExportLocalSupplierDTO" +
            "(s.id, s.name, count(p.id)) " +
            "FROM Supplier s " +
            "JOIN s.parts p " +
            "WHERE s.isImporter = false " +
            "GROUP BY s.id")
    List<ExportLocalSupplierDTO> getLocalSupplierPartCount();
}
