package com.example.xml_exercise_cardealer.carDealer.entities.suppliers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExportLocalSupplierDTO {

    @XmlAttribute
    private long id;

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "parts-count")
    private long partsCount;

}
