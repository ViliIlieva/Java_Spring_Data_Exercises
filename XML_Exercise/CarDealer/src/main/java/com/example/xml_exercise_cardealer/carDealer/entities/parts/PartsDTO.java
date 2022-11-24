package com.example.xml_exercise_cardealer.carDealer.entities.parts;

import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.SupplierDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartsDTO {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private SupplierDto supplier;
}
