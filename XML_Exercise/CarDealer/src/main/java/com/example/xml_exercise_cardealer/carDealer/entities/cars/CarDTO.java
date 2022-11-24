package com.example.xml_exercise_cardealer.carDealer.entities.cars;

import com.example.xml_exercise_cardealer.carDealer.entities.parts.PartsDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.parts.PartsNameAndPriceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private String make;
    private String model;
    private Long travelledDistance;
    Set<PartsDTO> parts;

    public BigDecimal getCarPrice(){
        return parts
                .stream()
                .map(PartsDTO::getPrice)
                .reduce(BigDecimal.ONE, BigDecimal::add );
    }

    public ExportCarWithPartsDTO carWithPartsDto(){
        ExportCarsDTO car = new ExportCarsDTO(make, model, travelledDistance);
        Set<PartsNameAndPriceDTO> parts =
                this.parts
                        .stream()
                        .map(CarDTO::partWithNameDto)
                        .collect(Collectors.toSet());

        return new ExportCarWithPartsDTO(car, parts);
    }

    public static PartsNameAndPriceDTO partWithNameDto(PartsDTO partDto){
        return new PartsNameAndPriceDTO(partDto.getName(), partDto.getPrice());
    }

}
