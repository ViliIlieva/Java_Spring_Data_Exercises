package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownImportDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Path path = Path.of ("src/main/resources/files/json/towns.json");
        return Files.readString (path);
    }

    @Override
    public String importTowns() throws IOException {
        String json = readTownsFileContent ();

        TownImportDTO[] townsDTOs = this.gson.fromJson (json, TownImportDTO[].class);

        return Arrays.stream (townsDTOs)
                .map (this::importTown)
                .collect(Collectors.joining ("\n"));
    }

    private String importTown(TownImportDTO dto) {
        Set<ConstraintViolation<TownImportDTO>> errors = this.validator.validate (dto);

        if (!errors.isEmpty ()){
            return "Invalid Town";
        }

        Town town = this.modelMapper.map (dto, Town.class);
        this.townRepository.save (town);
        return (String.format
                ("Successfully imported town %s - %d",
                        town.getTownName (), town.getPopulation ()));

    }
}
