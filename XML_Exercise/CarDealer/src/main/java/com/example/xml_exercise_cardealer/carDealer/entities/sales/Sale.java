package com.example.xml_exercise_cardealer.carDealer.entities.sales;

import com.example.xml_exercise_cardealer.carDealer.entities.cars.Car;
import com.example.xml_exercise_cardealer.carDealer.entities.customer.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    Car car;

    @OneToOne
    Customer customer;

    private BigDecimal discount;

}
