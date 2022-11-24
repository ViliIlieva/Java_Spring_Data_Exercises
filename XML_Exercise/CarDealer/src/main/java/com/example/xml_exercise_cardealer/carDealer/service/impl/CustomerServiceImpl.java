package com.example.xml_exercise_cardealer.carDealer.service.impl;

import com.example.xml_exercise_cardealer.carDealer.entities.customer.Customer;
import com.example.xml_exercise_cardealer.carDealer.entities.customer.ExportCustomerWrapperDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.customer.ExportCustomersOrderByBirthDateDTO;
import com.example.xml_exercise_cardealer.carDealer.repositories.CustomerRepository;
import com.example.xml_exercise_cardealer.carDealer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper ();
    }


    @Override
    public ExportCustomerWrapperDTO getAllCustomer() {

        List<Customer> customers = this.customerRepository.findAllByOrderByBirthDateAscYoungDriverDesc ();

        List<ExportCustomersOrderByBirthDateDTO> dtos =
                customers.stream ()
                        .map (u -> this.mapper.map (u, ExportCustomersOrderByBirthDateDTO.class))
                        .collect(Collectors.toList());

        return new ExportCustomerWrapperDTO (dtos);
    }
}
