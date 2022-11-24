package com.example.xml_exercise_cardealer.carDealer.entities.cars;

import com.example.xml_exercise_cardealer.carDealer.entities.parts.Parts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(name = "travelled_distance",nullable = false)
    private long travelledDistance;

    @ManyToMany
    Set<Parts> parts;


    public Car(String make, String model, Long travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }
}
