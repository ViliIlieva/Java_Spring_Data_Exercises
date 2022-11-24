package com.example.xml_exercise_cardealer.carDealer.entities.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver")
    private boolean youngDriver;

    public Customer(String name, LocalDateTime dateOfBirth, boolean isYoungDriver) {
        this.name = name;
        this.birthDate = dateOfBirth;
        this.youngDriver = isYoungDriver;
    }

}
