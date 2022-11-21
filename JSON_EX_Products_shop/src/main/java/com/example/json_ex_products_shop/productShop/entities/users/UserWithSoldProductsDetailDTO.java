package com.example.json_ex_products_shop.productShop.entities.users;

import com.example.json_ex_products_shop.productShop.entities.products.SoldProductsDTO;

import java.util.List;

public class UserWithSoldProductsDetailDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<SoldProductsDTO> soldProducts;

    public UserWithSoldProductsDetailDTO(){}

    public UserWithSoldProductsDetailDTO(String firstName, String lastName, Integer age, List<SoldProductsDTO> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProducts = soldProducts;
    }

    public Integer getCountSoldProduct(List<SoldProductsDTO> soldProducts){
        return soldProducts.size();
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

    public List<SoldProductsDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductsDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
