package com.example.json_ex_products_shop.productShop;

import com.example.json_ex_products_shop.productShop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;

    @Autowired
    public ProductShopRunner(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedUsers ();
        this.seedService.seedCategories ();

    }
}
