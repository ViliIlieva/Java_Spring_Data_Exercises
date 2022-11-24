package com.example.xml_exercise_cardealer.carDealer.entities.cars;

import com.example.xml_exercise_cardealer.carDealer.entities.parts.PartsNameAndPriceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportAllCarsWithTheirPartsWrapperDTO {

   @XmlElement(name = "car")
    private List<ExportCarWithPartsDTO> cars;
}
