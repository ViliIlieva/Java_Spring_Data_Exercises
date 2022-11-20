package com.example.xml_exercise.productShop.entities.categories;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public  boolean equals(Object o){
        if(this == o)return  true;
        if(o == null || getClass () != o.getClass ()) return false;
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode(){
        return  Objects.hash (id);
    }



}
