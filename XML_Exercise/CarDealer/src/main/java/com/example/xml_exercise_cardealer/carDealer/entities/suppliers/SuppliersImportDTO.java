package com.example.xml_exercise_cardealer.carDealer.entities.suppliers;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Getter
public class SuppliersImportDTO {

    @XmlElement(name = "supplier")
    List<SupplierImportDTO> suppliers;
}
