package com.example.football.service.impl;

import com.example.football.models.dto.ImportPlayerDTO;
import com.example.football.models.dto.ImportPlayerWrapperDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PlayerServiceImpl implements PlayerService {
    private final Path path = Path.of("src/main/resources/files/xml/players.xml");

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final StatRepository statRepository;

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository,
                             TownRepository townRepository, StatRepository statRepository)
            throws JAXBException {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;

        JAXBContext context = JAXBContext.newInstance (ImportPlayerWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller ();

        this.validator = Validation
                .buildDefaultValidatorFactory ()
                .getValidator ();

        this.modelMapper = new ModelMapper ();

        this.modelMapper.addConverter (ctx -> LocalDate.parse (ctx.getSource (),DateTimeFormatter.ofPattern ("dd/MM/yyyy")),
                String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count () > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString (path);
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {
        ImportPlayerWrapperDTO playerDTOs = (ImportPlayerWrapperDTO)
                this.unmarshaller.unmarshal (
                        new FileReader (path.toAbsolutePath ().toString ()));

        return playerDTOs
                .getPlayers ()
                .stream ()
                .map (this::importPlayer)
                .collect(Collectors.joining ("\n"));
    }

    private String importPlayer(ImportPlayerDTO dto) {
        Set<ConstraintViolation<ImportPlayerDTO>> errors =//проверяваме дали входните данни са валидни
                this.validator.validate (dto);

        if(!errors.isEmpty ()){
            return "Invalid Player";
        }
        Optional<Player> optPlayer =//проверява дали вече го има в репозиторито
                this.playerRepository.findByEmail (dto.getEmail ());

        if(optPlayer.isPresent ()){
            return "Invalid Player";
        }

        Optional<Team> team = this.teamRepository.findByName (dto.getTeam ().getName ());
        Optional<Town> town = this.townRepository.findByName (dto.getTown ().getName ());
        Optional<Stat> stat = this.statRepository.findById (dto.getStat ().getId ());

        Player player = this.modelMapper.map (dto, Player.class);

        player.setTeam (team.get ());
        player.setTown (town.get ());
        player.setStat (stat.get ());

        this.playerRepository.save (player);
        return String.format ("Successfully imported Player %s %s - %s",
                player.getFirstName (), player.getLastName (), player.getPosition ());
    }

    @Override
    public String exportBestPlayers() {
        LocalDate before = LocalDate.of(2003, 1, 1);
        LocalDate after = LocalDate.of(1995, 1, 1);

        List<Player> players = this.playerRepository
                .findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc
                        (after, before);

        return players
                .stream()
                .map(Player::toString)
                .collect(Collectors.joining("\n"));
    }


}
