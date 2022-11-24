package com.example.xml_exercise_cardealer.carDealer;

import com.example.xml_exercise_cardealer.carDealer.entities.cars.ExportAllCarsWithTheirPartsWrapperDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.cars.ExportCarMakeByToyotaWrapperDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.customer.ExportCustomerWrapperDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.ExportLocalSupplierWrapperDTO;
import com.example.xml_exercise_cardealer.carDealer.service.CarService;
import com.example.xml_exercise_cardealer.carDealer.service.CustomerService;
import com.example.xml_exercise_cardealer.carDealer.service.SeedService;
import com.example.xml_exercise_cardealer.carDealer.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.Path;

@Component
public class CarDealerRunner implements CommandLineRunner {

    private static final Path _01_ORDERED_CUSTOMERS =
            Path.of ("src/main/resources/outFile/ordered-customers.xml");
    private static final Path _02_CAR_MAKE_BY_TOYOTA =
            Path.of ("src/main/resources/outFile/carMakeByToyota.xml");
    private static final Path _03_LOCAL_SUPPLIERS =
            Path.of ("src/main/resources/outFile/localSuppliers.xml");
    private static final Path _04_ALL_CARS_WITH_THEIR_PARTS =
            Path.of ("src/main/resources/outFile/allCarsWithTheirParts.xml");

    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;

    @Autowired
    public CarDealerRunner(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {
//       this.seedService.seedSuppliers ();
//        this.seedService.seedParts ();
//        this.seedService.seedCars ();
//        this.seedService.seedCustomer ();
//        this.seedService.seedSales ();

//        this.customersOrderByBirthDate ();
//       this.carMakeByToyota();
//        this.exportLocalSuppliers();
        this.exportCarsWithTheirParts();
    }

    private void exportCarsWithTheirParts() throws JAXBException {
        File file = _04_ALL_CARS_WITH_THEIR_PARTS.toFile ();
        ExportAllCarsWithTheirPartsWrapperDTO cars = this.carService.getCarsWithTheirParts ();

        JAXBContext context = JAXBContext.newInstance (ExportAllCarsWithTheirPartsWrapperDTO.class);
        Marshaller marshaller = context.createMarshaller ();

        marshaller.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal (cars, file);
    }

    private void exportLocalSuppliers() throws JAXBException {
        File file = _03_LOCAL_SUPPLIERS.toFile ();
        ExportLocalSupplierWrapperDTO suppliers = this.supplierService.getAllLocalSuppliers ();

        JAXBContext context = JAXBContext.newInstance (ExportLocalSupplierWrapperDTO.class);
        Marshaller marshaller = context.createMarshaller ();

        marshaller.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal (suppliers, file);

    }

    private void carMakeByToyota() throws JAXBException {
        File file = _02_CAR_MAKE_BY_TOYOTA.toFile ();
        ExportCarMakeByToyotaWrapperDTO cars = this.carService.getCarsMakeByToyota ();

        JAXBContext context = JAXBContext.newInstance (ExportCarMakeByToyotaWrapperDTO.class);
        Marshaller marshaller = context.createMarshaller ();

        marshaller.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal (cars, file);
    }

    public void customersOrderByBirthDate() throws JAXBException {
        File file = _01_ORDERED_CUSTOMERS.toFile ();
        ExportCustomerWrapperDTO allCustomers = this.customerService.getAllCustomer ();

        final JAXBContext context = JAXBContext.newInstance (ExportCustomerWrapperDTO.class);
        Marshaller marshaller = context.createMarshaller ();

        marshaller.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal (allCustomers, file);

    }

}
