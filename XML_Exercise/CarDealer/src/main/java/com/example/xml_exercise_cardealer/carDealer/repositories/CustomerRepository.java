package com.example.xml_exercise_cardealer.carDealer.repositories;

import com.example.xml_exercise_cardealer.carDealer.entities.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customers ORDER BY RAND () LIMIT 1", nativeQuery = true)
    Customer getRandomEntity();


    List<Customer> findAllByOrderByBirthDateAscYoungDriverDesc();

}
