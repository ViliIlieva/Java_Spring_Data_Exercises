package com.example.gamestore.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToMany(targetEntity = Game.class, fetch = FetchType.EAGER)//взема ли поръчката да взема и продуктите към нея
    private Set<Game> games;

    public Order( ){
    }

    public Order(User user, Set<Game> games) {
        this.user = user;
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBuyer() {
        return user;
    }

    public void setBuyer(User user) {
        this.user = user;
    }

    public Set<Game> getProducts() {
        return games;
    }

    public void setProducts(Set<Game> games) {
        this.games = games;
    }
}
