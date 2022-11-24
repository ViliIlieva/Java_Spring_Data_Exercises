package com.example.xml_exercise_cardealer.carDealer.entities.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Getter
@Setter
public class CustomerImportDTO {

    @XmlAttribute
    private String name;

    @XmlElement(name = "birth-date")
    private XMLGregorianCalendar birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungerDriver;

}
