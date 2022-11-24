package com.example.xml_exercise_cardealer.carDealer.repositories;

import com.example.xml_exercise_cardealer.carDealer.entities.cars.Car;
import com.example.xml_exercise_cardealer.carDealer.entities.cars.ExportCarsDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.parts.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "select * from cars order by rand() limit 1", nativeQuery = true)
    Car getRandomEntity();

    @Query("select c from Car c where c.make = 'Toyota' " +
            "order by c.model, c.travelledDistance desc")
    List<Car> findByMakeOrderByModel();

    List<Car> findAll();

}
