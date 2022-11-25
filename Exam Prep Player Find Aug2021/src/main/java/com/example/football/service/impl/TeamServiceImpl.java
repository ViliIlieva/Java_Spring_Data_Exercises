package com.example.football.service.impl;

import com.example.football.models.dto.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
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
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;


    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;

        this.gson = new GsonBuilder ().create ();
        this.validator = Validation.buildDefaultValidatorFactory ().getValidator ();
        this.modelMapper = new ModelMapper ();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count () > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of ("src/main/resources/files/json/teams.json");
        return Files.readString (path);
    }

    @Override
    public String importTeams() throws IOException {
        String json = this.readTeamsFileContent ();

        TeamImportDTO[] teamsDTOs = this.gson.fromJson (json, TeamImportDTO[].class);

        return Arrays.stream (teamsDTOs)
                .map (this::importTeam)//в отделен метов имтортвам отбора
                .collect (Collectors.joining ("\n"));
    }

    private String importTeam(TeamImportDTO dto) {//в отделен метод да импортна отбора
        Set<ConstraintViolation<TeamImportDTO>> errors = this.validator.validate (dto);

        if (!errors.isEmpty ()) {
            return "Invalid Team";
        }
        //има ли такъв град вече в базата
        Optional<Team> optTeam = this.teamRepository.findByName (dto.getName ());

        if(optTeam.isPresent ()){//ако вече има такъв град
            return "Invalid Team";
        }

        Team team = this.modelMapper.map (dto, Team.class);
        Optional<Town> town = this.townRepository.findByName (dto.getTownName ());

        team.setTown (town.get ());
        this.teamRepository.save (team);

        return String.format ("Successfully imported Team %s - %d",
                team.getName (), team.getFanBase ());
    }
}
