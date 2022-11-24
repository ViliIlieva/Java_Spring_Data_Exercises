package com.example.xml_exercise_cardealer.carDealer.entities.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportCustomersOrderByBirthDateDTO {

    @XmlElement
    private long id;

    @XmlElement
    private String name;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungerDriver;
}
