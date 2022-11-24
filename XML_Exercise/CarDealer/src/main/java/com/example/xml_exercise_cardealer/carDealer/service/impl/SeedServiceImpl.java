package com.example.xml_exercise_cardealer.carDealer.service.impl;

import com.example.xml_exercise_cardealer.carDealer.constants.Discounts;
import com.example.xml_exercise_cardealer.carDealer.entities.cars.Car;
import com.example.xml_exercise_cardealer.carDealer.entities.cars.CarsImportDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.customer.Customer;
import com.example.xml_exercise_cardealer.carDealer.entities.customer.CustomersImportDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.parts.Parts;
import com.example.xml_exercise_cardealer.carDealer.entities.parts.PartsImportDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.sales.Sale;
import com.example.xml_exercise_cardealer.carDealer.entities.sales.SaleImportDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.Supplier;
import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.SuppliersImportDTO;
import com.example.xml_exercise_cardealer.carDealer.repositories.*;
import com.example.xml_exercise_cardealer.carDealer.service.SeedService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private static final Path SUPPLIERS_XML_PATH =
            Path.of ("src", "main", "resources", "suppliers.xml");
    private static final Path PARS_XML_PATH =
            Path.of ("src", "main", "resources", "parts.xml");
    private static final Path CARS_XML_PATH =
            Path.of ("src", "main", "resources", "cars.xml");
    private static final Path CUSTOMER_XML_PATH =
            Path.of ("src", "main", "resources", "customers.xml");

    private static final String CUSTOM_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    private final ModelMapper mapper;
    private final Random random;
    private final SupplierRepository supplierRepository;
    private final PartsRepository partsRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public SeedServiceImpl(Gson json, SupplierRepository supplierRepository,
                           PartsRepository partsRepository, CarRepository carRepository,
                           CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partsRepository = partsRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.mapper = new ModelMapper ();
        this.random = new Random ();
    }

    @Override
    public void seedSuppliers() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance (SuppliersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller ();

        BufferedReader xmlReader = Files.newBufferedReader (SUPPLIERS_XML_PATH);
        SuppliersImportDTO suppliersDTOs = (SuppliersImportDTO) unmarshaller.unmarshal (xmlReader);

        List<Supplier> entities = suppliersDTOs.getSuppliers ().stream ()
                .map (dto -> this.mapper.map (dto, Supplier.class))
                .collect (Collectors.toList ());

        this.supplierRepository.saveAll (entities);
    }

    @Override
    public void seedParts() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance (PartsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller ();

        BufferedReader xmlReader = Files.newBufferedReader (PARS_XML_PATH);
        PartsImportDTO importDTOs = (PartsImportDTO) unmarshaller.unmarshal (xmlReader);

        List<Parts> entities = importDTOs.getParts ().stream ()
                .map (dto -> new Parts (dto.getName (), dto.getPrice (), dto.getQuantity ()))
                .map (this::setRandomSupplier)
                .collect (Collectors.toList ());
        this.partsRepository.saveAll (entities);
    }

    @Override
    @Transactional
    public void seedCars() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance (CarsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller ();

        BufferedReader xmlReader = Files.newBufferedReader (CARS_XML_PATH);
        CarsImportDTO importDTOs = (CarsImportDTO) unmarshaller.unmarshal (xmlReader);

        List<Car> entities = importDTOs.getCars ().stream ()
                .map (dto -> new Car (dto.getMake (), dto.getModel (), dto.getTravelledDistance ()))
                .map (this::setRandomParts)
                .collect (Collectors.toList ());
        this.carRepository.saveAll (entities);
    }

    @Override
    @Transactional
    public void seedCustomer() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance (CustomersImportDTO.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller ();

        BufferedReader xmlReader = Files.newBufferedReader (CUSTOMER_XML_PATH);

        CustomersImportDTO importDTOs = (CustomersImportDTO) jaxbUnmarshaller.unmarshal (xmlReader);

        List<Customer> entities = importDTOs.getCustomers ().stream ()
                .map (dto -> new Customer (dto.getName (),
                        dto.getBirthDate ().toGregorianCalendar ().toZonedDateTime ().toLocalDateTime (),
                        dto.isYoungerDriver ()))
                .collect (Collectors.toList ());
        this.customerRepository.saveAll (entities);
    }

    @Override
    public void seedSales() {
        int totalSales = random.nextInt (20, (int) carRepository.count ());

        List<Sale> entities = new ArrayList<> ();
        for (int i = 0; i <totalSales; i++) {
            SaleImportDTO saleImportDTOs = new SaleImportDTO (setRandomCustomer (),
                    setRandomCar (), setRandomDiscount ());
            entities.add (mapper.map (saleImportDTOs, Sale.class));
        }
        this.saleRepository.saveAll (entities);
    }

    private Customer setRandomCustomer(){
        return this.customerRepository.getRandomEntity ();
    }

    private Car setRandomCar(){
        return this.carRepository.getRandomEntity ();
    }

    private BigDecimal setRandomDiscount(){
        return Discounts.getRandomDiscount ().discountValue;
    }

    private Parts setRandomSupplier(Parts parts) {
        long suppliersDBContest = this.supplierRepository.count ();

        long randomId = random.nextLong ((long) suppliersDBContest) + 1;
        Optional<Supplier> randomSupplier = this.supplierRepository.findById (randomId);

        parts.setSupplier (randomSupplier.get ());

        return parts;
    }

    private Car setRandomParts(Car car) {
        Set<Parts> parts = new HashSet<> ();
        Random random = new Random ();
        int count = (int) ((Math.random () * (6 - 3)) + 3); //произволно число от 10 до 20

        long partsDBCount = this.partsRepository.count ();//броя на всички части

        for (int i = 0; i < count; i++) {
            long randomId = random.nextLong ((long) partsDBCount) + 1;

            Optional<Parts> randomParts = this.partsRepository.findById (randomId);

            parts.add (randomParts.get ());
        }
        car.setParts (parts);
        return car;
    }


}


