package com.example.json_ex_products_shop.productShop.services;

import com.example.json_ex_products_shop.productShop.entities.categories.Category;
import com.example.json_ex_products_shop.productShop.entities.categories.CategoryImportDTO;
import com.example.json_ex_products_shop.productShop.entities.products.Product;
import com.example.json_ex_products_shop.productShop.entities.products.ProductImportDTO;
import com.example.json_ex_products_shop.productShop.entities.users.User;
import com.example.json_ex_products_shop.productShop.entities.users.UserImportDTO;
import com.example.json_ex_products_shop.productShop.repositories.CategoryRepository;
import com.example.json_ex_products_shop.productShop.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path USERS_JSON_PATH =
            Path.of ("src","main", "resources", "productshop","users.json");
    private static final Path CATEGORIES_JSON_PATH =
            Path.of ("src","main","resources","productshop","categories.json");

    private static final Path PRODUCTS_JSON_PATH =
            Path.of ("src","main","resources","productshop","products.json");

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;

        this.modelMapper = new ModelMapper ();
        this.gson = new GsonBuilder ()
                .setPrettyPrinting ()
                .create ();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader fileReader = new FileReader (USERS_JSON_PATH.toAbsolutePath ().toString ());
        UserImportDTO[] userImportDTOS = this.gson.fromJson (fileReader, UserImportDTO[].class);

        List<User> users = Arrays.stream (userImportDTOS)
                .map(importDTO -> this.modelMapper.map (importDTO, User.class))
                .collect(Collectors.toList ());

        this.userRepository.saveAll (users);
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader fileReader = new FileReader (CATEGORIES_JSON_PATH.toAbsolutePath ().toString ());
        CategoryImportDTO[] categoryImportDTOS = this.gson.fromJson (fileReader, CategoryImportDTO[].class);

        List<Category> categories = Arrays.stream (categoryImportDTOS)
                .map(importDTO -> this.modelMapper.map (importDTO, Category.class))
                .collect(Collectors.toList ());

        this.categoryRepository.saveAll (categories);

    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader (PRODUCTS_JSON_PATH.toAbsolutePath ().toString ());
        ProductImportDTO[] productImportDTOS = this.gson.fromJson (fileReader, ProductImportDTO[].class);

        List<Product> categories = Arrays.stream (productImportDTOS)
                .map(importDTO -> this.modelMapper.map (importDTO, Product.class))
                .map(this::setRandomSeller)
                .collect(Collectors.toList ());
    }

    private Product setRandomSeller(Product product){
        User seller = this.userService.getRandomUser();
        product.setSeller(seller);
        return  product;
    }
}
