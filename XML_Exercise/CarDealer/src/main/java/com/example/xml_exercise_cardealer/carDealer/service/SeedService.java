package com.example.xml_exercise_cardealer.carDealer.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SeedService {
    void seedSuppliers() throws JAXBException, IOException;
    void seedParts() throws JAXBException, IOException;
    void seedCars() throws JAXBException, IOException;
    void seedCustomer() throws JAXBException, IOException;
    void seedSales();


}
