package com.example.football.service.impl;

import com.example.football.models.dto.TownImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;

        this.gson = new GsonBuilder ().create ();
        this.validator = Validation.buildDefaultValidatorFactory ().getValidator ();
        this.modelMapper = new ModelMapper ();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count () > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        Path path = Path.of("src/main/resources/files/json/towns.json");
        return Files.readString (path);
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent ();

        TownImportDTO[] importTownDTOS = this.gson.fromJson (json, TownImportDTO[].class);

        List<String> result = new ArrayList<> ();
        for (TownImportDTO importTownDTO : importTownDTOS) {
            //сет от всички грешки които са се натрупали при импортването на данните
            Set<ConstraintViolation<TownImportDTO>> validationErrors =
                    this.validator.validate (importTownDTO);

        if (validationErrors.isEmpty ()){//ако града е валиден спрямо анотациите на полетата в ДТО импорт
            //дали града съществува в базата
            Optional<Town> optTown = this.townRepository
                    .findByName(importTownDTO.getName ());

            if(optTown.isEmpty ()){//ако града не съществува в базата да го добавя
                Town town = this.modelMapper.map (importTownDTO, Town.class);
                this.townRepository.save (town);

                result.add (String.format
                        ("Successfully imported Town %s - %d",
                                town.getName (), town.getPopulation ()));

            }else {//ако града съществува вече в базата
                result.add ("Invalid Town");
            }

        }else {//ако града е невалиден спрямо анотациите
            result.add ("Invalid Town");
        }
        }

        return String.join ("\n", result);
    }
}
