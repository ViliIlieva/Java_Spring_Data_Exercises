package com.example.xml_exercise_cardealer.carDealer.entities.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Getter
public class CustomersImportDTO {

    @XmlElement(name = "customer")
    private List<CustomerImportDTO> customers;
}
