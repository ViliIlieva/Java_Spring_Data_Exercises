package com.example.xml_exercise_cardealer.carDealer.entities.suppliers;

import com.example.xml_exercise_cardealer.carDealer.entities.parts.Parts;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Parts.class, mappedBy = "supplier")
    Set<Parts> parts;

    public Supplier(){}

    public Supplier(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    @Column(name = "is_importer")
    private boolean isImporter;

}
