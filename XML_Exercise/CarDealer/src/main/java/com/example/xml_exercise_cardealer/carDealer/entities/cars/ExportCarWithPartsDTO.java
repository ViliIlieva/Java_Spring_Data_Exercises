package com.example.xml_exercise_cardealer.carDealer.entities.cars;

import com.example.xml_exercise_cardealer.carDealer.entities.parts.PartsNameAndPriceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportCarWithPartsDTO {

    @XmlElement
    private ExportCarsDTO car;

    @XmlElement(name = "")
    public Set<PartsNameAndPriceDTO> parts;
}
