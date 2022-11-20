package com.example.xml_exercise.productShop.services;

import com.example.xml_exercise.productShop.entities.categories.Category;
import com.example.xml_exercise.productShop.entities.categories.CategoryImportDTO;
import com.example.xml_exercise.productShop.entities.products.Product;
import com.example.xml_exercise.productShop.entities.products.ProductsImportDTO;
import com.example.xml_exercise.productShop.entities.users.User;
import com.example.xml_exercise.productShop.entities.users.UsersImportDTO;
import com.example.xml_exercise.productShop.repositories.CategoryRepository;
import com.example.xml_exercise.productShop.repositories.ProductRepository;
import com.example.xml_exercise.productShop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path USERS_XML_PATH =
            Path.of ("src", "main", "resources", "productshop", "users.xml");
    private static final Path CATEGORIES_XML_PATH =
            Path.of ("src", "main", "resources", "productshop", "categories.xml");

    private static final Path PRODUCTS_XML_PATH =
            Path.of ("src", "main", "resources", "productshop", "products.xml");

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

        this.mapper = new ModelMapper ();
    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance (UsersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller ();

        BufferedReader xmlReader = Files.newBufferedReader (USERS_XML_PATH);
        UsersImportDTO usersDTOs = (UsersImportDTO) unmarshaller.unmarshal (xmlReader);

        List<User> entities = usersDTOs.getUsers ().stream ()
                .map (dto -> this.mapper.map (dto, User.class))
                .collect (Collectors.toList ());

        this.userRepository.saveAll (entities);
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance (CategoryImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller ();//стринг към обекти

        FileReader xmlReader = new FileReader (CATEGORIES_XML_PATH.toAbsolutePath ().toString ());
        CategoryImportDTO importDTO = (CategoryImportDTO) unmarshaller.unmarshal (xmlReader);

        List<Category> entities = importDTO.getCategories ().stream ()
                .map (c -> new Category (c.getName ())).toList ();
        this.categoryRepository.saveAll (entities);
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance (ProductsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller ();//стринг към обекти

        BufferedReader xmlReader = Files.newBufferedReader (PRODUCTS_XML_PATH);
        ProductsImportDTO importDTOs = (ProductsImportDTO) unmarshaller.unmarshal (xmlReader);

        List<Product> entities = importDTOs.getProducts ().stream ()
                .map (dto -> new Product (dto.getName (), dto.getPrice ()))
                .map(this::setRandomCategories)
                .map (this::setRandomSeller)
                .map (this::setRandomBuyer)
                .collect (Collectors.toList ());
        this.productRepository.saveAll (entities);

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
        return product;
    }

    private Product setRandomBuyer(Product product) {
        //ако взетата цена е < от 944 -> -1; ако е = на тази цена е 0; ако е > 1
        //ако не е по-голяма от 0 ще има купувач
        if (product.getPrice ().compareTo (BigDecimal.valueOf (944)) > 0) {
            return product;
        }
        //0...4+1 = 1..5
        Optional<User> buyer = getRandomUser ();
        product.setBuyer (buyer.get ());
        return product;
    }

    private Product setRandomSeller(Product product) {
        Optional<User> seller = getRandomUser ();
        product.setSeller (seller.get ());
        return product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count ();

        Long randomUserId = new Random ().nextLong ((Long) usersCount) + 1;
        Optional<User> seller = this.userRepository.findById (randomUserId);
        return seller;
    }

}
