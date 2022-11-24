package com.example.xml_exercise_cardealer.carDealer.entities.cars;

import com.example.xml_exercise_cardealer.carDealer.entities.cars.ExportCarMakeByToyotaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportCarMakeByToyotaWrapperDTO {

    @XmlElement(name = "car")
    List<ExportCarMakeByToyotaDTO> cars;
}
