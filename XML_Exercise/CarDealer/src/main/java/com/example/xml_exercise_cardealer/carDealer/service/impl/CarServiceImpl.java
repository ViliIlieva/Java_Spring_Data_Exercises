package com.example.xml_exercise_cardealer.carDealer.service.impl;

import com.example.xml_exercise_cardealer.carDealer.entities.cars.*;
import com.example.xml_exercise_cardealer.carDealer.entities.parts.Parts;
import com.example.xml_exercise_cardealer.carDealer.entities.parts.PartsNameAndPriceDTO;
import com.example.xml_exercise_cardealer.carDealer.repositories.CarRepository;
import com.example.xml_exercise_cardealer.carDealer.repositories.PartsRepository;
import com.example.xml_exercise_cardealer.carDealer.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartsRepository partsRepository;
    private ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartsRepository partsRepository) {
        this.carRepository = carRepository;
        this.partsRepository = partsRepository;
        this.mapper = new ModelMapper ();
    }

    @Override
    public ExportCarMakeByToyotaWrapperDTO getCarsMakeByToyota() {
        List<Car> cars = this.carRepository.findByMakeOrderByModel ();

        List<ExportCarMakeByToyotaDTO> dtos =
                cars.stream ()
                        .map (c -> this.mapper.map (c, ExportCarMakeByToyotaDTO.class))
                        .toList ();

        return new ExportCarMakeByToyotaWrapperDTO (dtos);
    }

    @Override
    @Transactional
    public ExportAllCarsWithTheirPartsWrapperDTO getCarsWithTheirParts() {

        List<ExportCarWithPartsDTO> carWithPartsDTOs = this.carRepository
                .findAll ()
                .stream ()
                .map (car -> mapper.map (car, CarDTO.class))
                .map (CarDTO::carWithPartsDto)
                .toList ();

        return new ExportAllCarsWithTheirPartsWrapperDTO (carWithPartsDTOs);
    }
}
