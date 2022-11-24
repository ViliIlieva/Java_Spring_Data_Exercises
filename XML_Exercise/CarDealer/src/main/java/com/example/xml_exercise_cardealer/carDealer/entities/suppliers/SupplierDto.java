package com.example.xml_exercise_cardealer.carDealer.entities.suppliers;

import com.example.xml_exercise_cardealer.carDealer.entities.parts.PartsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierDto {
    private String name;
    private boolean isImporter;
    Set<PartsDTO> parts;
}
