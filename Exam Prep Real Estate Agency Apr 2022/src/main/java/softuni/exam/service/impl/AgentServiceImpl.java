package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

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
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;

    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;

        this.gson = new GsonBuilder ().create ();
        this.validator = Validation.buildDefaultValidatorFactory ().getValidator ();
        this.modelMapper = new ModelMapper ();
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count () > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        Path path = Path.of ("src/main/resources/files/json/agents.json");
        return Files.readString (path);
    }

    @Override
    public String importAgents() throws IOException {
        String json = this.readAgentsFromFile ();

        AgentImportDTO[] agentDTOs = this.gson.fromJson (json, AgentImportDTO[].class);

        return Arrays.stream (agentDTOs)
                .map (this::importAgent)
                .collect(Collectors.joining ("\n"));
    }

    private String  importAgent(AgentImportDTO dto) {
        Set<ConstraintViolation<AgentImportDTO>> errors = this.validator.validate (dto);

        if(!errors.isEmpty ()){
            return "Invalid agent";
        }
        Optional<Agent> optAgent = this.agentRepository.findByFirstName(dto.getFirstName ());

        if(optAgent.isPresent ()){
            return "Invalid agent";
        }

        Agent agent = this.modelMapper.map (dto, Agent.class);
        Optional<Town>town = this.townRepository.findByTownName (dto.getTown ());

        agent.setTown (town.get ());
        this.agentRepository.save (agent);

        return String.format ("Successfully imported agent - %s %s",
                agent.getFirstName (), agent.getLastName ());

    }
}
