package com.example.json_ex_products_shop.productShop;

import com.example.json_ex_products_shop.productShop.entities.products.ProductWithoutBuyerDTO;
import com.example.json_ex_products_shop.productShop.entities.users.UserWithSoldProductsDTO;
import com.example.json_ex_products_shop.productShop.services.UserService;
import com.example.json_ex_products_shop.productShop.services.ProductService;
import com.example.json_ex_products_shop.productShop.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;

    private final Gson gson;


    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;

        this.gson = new GsonBuilder ()
                .setPrettyPrinting ()
                .create ();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedUsers ();
//        this.seedService.seedCategories ();
//        this.seedService.seedProducts ();

//        Query 1 – Products in Range
//        productsBetweenPriceWithoutBuyer ();

//        Query 2 – Successfully Sold Products
//        successfullySoldProducts ();

    }

    private void successfullySoldProducts() {
        List<UserWithSoldProductsDTO> userWithSoldProducts = this.userService.getUserWithSoldProducts ();
        String json = this.gson.toJson (userWithSoldProducts);
        System.out.println (json);
    }

    private void productsBetweenPriceWithoutBuyer() {
        List<ProductWithoutBuyerDTO> output = this.productService.getProductsInPriceRangeForSell (500, 1000);
        String json = this.gson.toJson (output);
        System.out.println (json);
    }
}
