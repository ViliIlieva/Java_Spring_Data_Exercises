package com.example.xml_exercise.productShop;

import com.example.xml_exercise.productShop.entities.products.ExportProductsInRangeDTO;
import com.example.xml_exercise.productShop.entities.users.ExportSellersDTO;
import com.example.xml_exercise.productShop.services.ProductService;
import com.example.xml_exercise.productShop.services.SeedService;
import com.example.xml_exercise.productShop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedUsers ();
//        this.seedService.seedCategories ();
//        this.seedService.seedProducts();

//        this.productsInRange();
//        this.findUsersWithSoldProducts();

    }

    private void findUsersWithSoldProducts() throws JAXBException {
        ExportSellersDTO result = this.userService.findAllWithSoldProducts ();

        JAXBContext output = JAXBContext.newInstance (ExportSellersDTO.class);
        Marshaller marshaller = output.createMarshaller ();

        marshaller.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal (result, System.out);
    }

    private void productsInRange() throws JAXBException {
        ExportProductsInRangeDTO inRange = this.productService.getInRange (500, 1000);

        JAXBContext output = JAXBContext.newInstance (ExportProductsInRangeDTO.class);
        Marshaller marshaller = output.createMarshaller ();

        marshaller.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal (inRange, System.out);
    }
}
