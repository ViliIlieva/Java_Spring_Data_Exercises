package com.example.xml_exercise.productShop.entities.users;

import com.example.xml_exercise.productShop.entities.products.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    private Integer age;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller")
    private List<Product> sellingItems;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer")
    private List<Product> itemsBought;

    @ManyToMany
    private Set<User> friends;

    public User(){
        this.sellingItems = new ArrayList<> ();
        this.itemsBought = new ArrayList<> ();
        this.friends = new HashSet<> ();
    }

    public User(String firstName, String lastName, Integer age) {
        this();

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Product> getSellingItems() {
        return sellingItems;
    }

    public void setSellingItems(List<Product> sellingItems) {
        this.sellingItems = sellingItems;
    }

    public List<Product> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<Product> itemsBought) {
        this.itemsBought = itemsBought;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getFullName(){
        if(this.firstName == null){
            return this.lastName;
        }
        return this.firstName + " " + this.lastName;
    }
}
