package com.example.xml_exercise_cardealer.carDealer.entities.parts;

import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.Supplier;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parts")
@Getter
@Setter
public class Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    private Supplier supplier;

    public Parts(){}

    public Parts(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
