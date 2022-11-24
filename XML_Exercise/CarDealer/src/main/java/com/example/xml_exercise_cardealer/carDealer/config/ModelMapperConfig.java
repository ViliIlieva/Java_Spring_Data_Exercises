package com.example.xml_exercise_cardealer.carDealer.config;

import com.example.xml_exercise_cardealer.carDealer.entities.customer.Customer;
import com.example.xml_exercise_cardealer.carDealer.entities.customer.ExportCustomersOrderByBirthDateDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        customerToCustomerOrderBirthdateDto(mapper);

        return mapper;
    }

    private void customerToCustomerOrderBirthdateDto(ModelMapper mapper) {
        Converter<LocalDateTime, String> toDateToString =
                ctx -> DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ctx.getSource());

        mapper.createTypeMap(Customer.class, ExportCustomersOrderByBirthDateDTO.class)
                .addMapping(Customer::getBirthDate, ExportCustomersOrderByBirthDateDTO::setBirthDate);
    }
}
