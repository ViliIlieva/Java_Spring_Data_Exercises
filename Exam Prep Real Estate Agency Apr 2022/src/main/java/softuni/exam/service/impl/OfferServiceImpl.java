package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferImportDTO;
import softuni.exam.models.dto.OfferImportWrapperDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final Path path = Path.of ("src/main/resources/files/xml/offers.xml");

    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository,
                            ApartmentRepository apartmentRepository) throws JAXBException {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;

        JAXBContext context = JAXBContext.newInstance (OfferImportWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller ();

        this.validator = Validation
                .buildDefaultValidatorFactory ()
                .getValidator ();

        this.modelMapper = new ModelMapper ();

        this.modelMapper.addConverter (ctx -> LocalDate.parse (ctx.getSource (), DateTimeFormatter.ofPattern ("dd/MM/yyyy")),
                String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count () > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString (path);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        OfferImportWrapperDTO offerDTOs = (OfferImportWrapperDTO)
                this.unmarshaller.unmarshal (
                new FileReader (path.toAbsolutePath ().toString ()));

        List<String> result = new ArrayList<> ();

        for (OfferImportDTO dto : offerDTOs.getOffers ()) {
            Set<ConstraintViolation<OfferImportDTO>> errors =
                    this.validator.validate (dto);

            if(errors.isEmpty ()){
                Agent agent =//дали агента го има в базата
                        this.agentRepository.findByFirstNameOrderByFirstName (dto.getAgent ().getName ());

                if(agent != null){//ако го има можем да добавяме офертата
                    //вземам и апартамента
                    Apartment apartment =
                            this.apartmentRepository.findByIdOrderById (dto.getApartment ().getId ());
                    Offer offer = this.modelMapper.map (dto, Offer.class);

                    offer.setAgent (agent);
                    offer.setApartment (apartment);

                    this.offerRepository.save (offer);

                    result.add (String.format ("Successfully imported offer %.2f", offer.getPrice ()));
                }else {
                    result.add ("Invalid offer");
                }
            }else {
                result.add ("Invalid offer");
            }
        }
        return String.join ("\n", result);
    }

    @Override
    public String exportOffers() {
//        String apartmentType = "1";

        List<Offer> offers = this.offerRepository.findAllByApartmentType ();

        return offers
                .stream ()
                .map (Offer::toString)
                .collect(Collectors.joining("\n"));


    }
}

















