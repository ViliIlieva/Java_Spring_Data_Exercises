package com.example.json_ex_products_shop.productShop.entities.users;

import com.example.json_ex_products_shop.productShop.entities.products.SoldProductsDTO;

import java.util.List;

public class UserWithSoldProductsDTO {
    private String firstName;
    private String lastName;
    private List<SoldProductsDTO> itemsBought;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<SoldProductsDTO> getItemsBought() {
        return itemsBought;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setItemsBought(List<SoldProductsDTO> itemsBought) {
        this.itemsBought = itemsBought;
    }
}
