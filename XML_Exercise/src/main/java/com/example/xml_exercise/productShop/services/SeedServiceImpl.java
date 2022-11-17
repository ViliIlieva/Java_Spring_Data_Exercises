package com.example.xml_exercise.productShop.services;

import com.example.xml_exercise.productShop.entities.categories.Category;
import com.example.xml_exercise.productShop.entities.products.Product;
import com.example.xml_exercise.productShop.entities.users.User;
import com.example.xml_exercise.productShop.repositories.CategoryRepository;
import com.example.xml_exercise.productShop.repositories.ProductRepository;
import com.example.xml_exercise.productShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path USERS_XML_PATH =
            Path.of ("src","main", "resources", "productshop","users.xml");
    private static final Path CATEGORIES_XML_PATH =
            Path.of ("src","main","resources", "productshop","categories.xml");

    private static final Path PRODUCTS_XML_PATH =
            Path.of ("src","main","resources", "productshop","products.xml");

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {

    }

    @Override
    public void seedCategories() throws FileNotFoundException {

    }

    @Override
    public void seedProducts() throws FileNotFoundException {

    }

    private Product setRandomCategories(Product product) {
        long categoriesDBCount = this.categoryRepository.count ();

        Random random = new Random ();
        int count = random.nextInt ((int) categoriesDBCount);

        Set<Category> categories = new HashSet<> ();
        for (int i = 0; i < count; i++) {
            long randomId = random.nextLong ((long) categoriesDBCount) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById (randomId);

            categories.add (randomCategory.get ());
        }
        product.setCategories (categories);
        return  product;
    }

    private Product setRandomBuyer(Product product){
        //ако взетата цена е < от 944 -> -1; ако е = на тази цена е 0; ако е > 1
        //ако не е по-голяма от 0 ще има купувач
        if(product.getPrice ().compareTo (BigDecimal.valueOf (944)) > 0){
            return product;
        }
        Optional<User> buyer = getRandomUser ();
        product.setBuyer (buyer.get ());
        return  product;
    }

    private Product setRandomSeller(Product product){
        Optional<User> seller = getRandomUser ();
        product.setSeller(seller.get());
        return  product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count();

        Long randomUserId = new Random ().nextLong ((Long) usersCount) + 1;
        Optional<User> seller = this.userRepository.findById (randomUserId);
        return seller;
    }

}
