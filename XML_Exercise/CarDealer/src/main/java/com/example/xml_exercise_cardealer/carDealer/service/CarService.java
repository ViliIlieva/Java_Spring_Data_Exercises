package com.example.xml_exercise_cardealer.carDealer.service;

import com.example.xml_exercise_cardealer.carDealer.entities.cars.ExportAllCarsWithTheirPartsWrapperDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.cars.ExportCarMakeByToyotaWrapperDTO;

public interface CarService {

    ExportCarMakeByToyotaWrapperDTO getCarsMakeByToyota();

    ExportAllCarsWithTheirPartsWrapperDTO getCarsWithTheirParts();
}
